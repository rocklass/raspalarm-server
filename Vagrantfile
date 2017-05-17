$vagrantFileApiVersion = "2"
$script = <<SCRIPT
	pacman -Syyu --noconfirm
	pacman -S docker --noconfirm
SCRIPT

Vagrant.configure($vagrantFileApiVersion) do |config|
	config.vm.box = "terrywang/archlinux"
	config.vm.synced_folder ".", "/src"
	config.vm.provision "shell", inline: $script
end
