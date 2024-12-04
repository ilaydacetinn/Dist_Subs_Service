require 'socket'

# Configuration sınıfı tanımı
class Configuration
  attr_accessor :fault_tolerance_level, :method

  def initialize(fault_tolerance_level, method)
    @fault_tolerance_level = fault_tolerance_level
    @method = method
  end
end

# dist_subs.conf dosyasını okuma
config_file_path = File.join(Dir.pwd, "dist_subs.conf")
unless File.exist?(config_file_path)
  puts "dist_subs.conf dosyası bulunamadı!"
  exit
end

fault_tolerance_level = nil
File.foreach(config_file_path) do |line|
  if line.strip =~ /^fault_tolerance_level\s*=\s*(\d+)$/
    fault_tolerance_level = $1.to_i
  end
end

if fault_tolerance_level.nil?
  puts "dist_subs.conf dosyasından hata tolerans seviyesi okunamadı!"
  exit
end

# Configuration nesnesi oluşturma
config = Configuration.new(fault_tolerance_level, "STRT")

# Sunuculara komut gönderme
servers = [
  { name: "Server1", host: "localhost", port: 5001 },
  { name: "Server2", host: "localhost", port: 5002 },
  { name: "Server3", host: "localhost", port: 5003 }
]

servers.each do |server|
  begin
    # Sunucuya bağlan
    socket = TCPSocket.new(server[:host], server[:port])

    # Komut gönder
    message = "fault_tolerance_level=#{config.fault_tolerance_level} method=#{config.method}"
    socket.puts(message)
    puts "#{server[:name]} sunucusuna komut gönderildi: #{message}"

    # Yanıt al
    response = socket.gets
    puts "#{server[:name]} yanıt verdi: #{response.strip}" unless response.nil?

    socket.close
  rescue => e
    puts "#{server[:name]} bağlantı hatası: #{e.message}"
  end
end

puts "Tüm sunuculara komut gönderimi tamamlandı."
