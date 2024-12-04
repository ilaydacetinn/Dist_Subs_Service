require 'socket'

# Sunucuların listesi
servers = [
  { name: "Server1", host: "localhost", port: 5001 },
  { name: "Server2", host: "localhost", port: 5002 },
  { name: "Server3", host: "localhost", port: 5003 }
]

responding_servers = []

# Başlangıç komutu gönder
servers.each do |server|
  begin
    socket = TCPSocket.new(server[:host], server[:port])
    message = "fault_tolerance_level=1 method=STRT"
    socket.puts(message)

    response = socket.gets
    if response && response.include?("response=YEP")
      responding_servers << server
      puts "#{server[:name]} yanıt verdi: #{response.strip}"
    end

    socket.close
  rescue => e
    puts "#{server[:name]} bağlantı hatası: #{e.message}"
  end
end

# Kapasite sorguları
loop do
  responding_servers.each do |server|
    begin
      socket = TCPSocket.new(server[:host], server[:port])

      # Kapasite sorgusu gönder
      socket.puts("demand=CPCTY response=null")
      response = socket.gets

      if response
        puts "#{server[:name]} yanıt verdi: #{response.strip}"
      end

      socket.close
    rescue => e
      puts "#{server[:name]} kapasite sorgusunda hata: #{e.message}"
    end
  end

  sleep 5
end
