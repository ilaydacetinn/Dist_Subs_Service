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
servers = ["Server1.java", "Server2.java", "Server3.java"]

servers.each do |server|
  puts "Sunucuya komut gönderiliyor: #{server} - #{config.method}"
  # Burada ilgili komutları sunuculara gönderme işlemleri yapılabilir.
end

puts "Tüm sunucular başlatıldı."

