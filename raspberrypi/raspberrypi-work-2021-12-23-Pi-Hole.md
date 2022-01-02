# Raspberry Pi 3

References

* https://www.youtube.com/watch?v=pWM4PCqrVc8
* https://docs.google.com/document/d/1Dx0ULT92xrry8st9WiNmAKN5KsdqH_XYhNgyWsu8WOo/edit
* 
TODO:

* Pi-Hole - DNS
* LibreNMS -
* Unifi Controller - Ubiquity wifi management

Using

* Windows 10
* Raspberry Pi Imager


Using Raspberry Pi OS

From

* https://www.raspberrypi.com/software/operating-systems/

```
Raspberry Pi OS Lite
Release date: October 30th 2021
Kernel version: 5.10
Size: 463MB
```

Raspberry Pi Imager - Custom Image

Under `Operating Systems`/`Choose OS`, there is option `Use custom`

Enter `<CTRL>-<SHIFT>-X` to enter advance options menu, includes:
* set hostname
* enable ssh

After writing the image to the thumb drive, mount and create a blank `ssh` file in the `boot` partition.

(IE: use 'touch'/echo)

Wifi setup

Create `wpa_supplicant.conf` file with contents:

```
country=US
ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
update_config=1
network={
  ssid="YOUR_NETWORK"
  scan_ssid=1
  psk="YOUR_PASSWORD"
  key_mgmt=WPA-PSK
}
```

## login

Find the ip address and connect/login:

```
username: pi
password: raspberry
```

## Updating

```
sudo apt update -y
sudo apt upgrade -y
sudo apt dist-upgrade -y

sudo apt-get update -y
sudo apt-get upgrade -y
sudo apt dist-upgrade -y

sudo raspi-config
```

## VNC

Set in:

```
sudo raspi-config
```

## Installing Pi-Hole

```
curl -sSL https://install.pi-hole.net | bash
```

TODO: configure Pi-Hole
