$vagrantFileApiVersion = "2"
$script = <<SCRIPT
echo "Updating packages"
sudo pacman -Syyu --noconfirm
echo "Installing Docker"
sudo pacman -S docker --noconfirm
echo "Configuring Docker"
sudo usermod -aG docker vagrant
sudo systemctl enable docker
sudo mkdir -p /etc/systemd/system/docker.service.d
sudo cat > /etc/systemd/system/docker.service.d/override.conf <<EOF
[Service]
ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock --tls=false
EOF
sudo mkdir -p /etc/systemd/system/docker.socket.d
sudo cat > /etc/systemd/system/docker.socket.d/socket.conf <<EOF
[Socket]
ListenStream=0.0.0.0:2375
EOF
SCRIPT

Vagrant.configure($vagrantFileApiVersion) do |config|
	config.vm.box = "terrywang/archlinux"
	config.vm.synced_folder ".", "/src"
	config.vm.provision "shell", inline: $script
	config.vm.network :forwarded_port, host: 2375, guest: 2375
	config.vm.network :forwarded_port, host: 4243, guest: 4243
	config.vm.network :forwarded_port, host: 8080, guest: 8080
end
