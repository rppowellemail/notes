# Raspberry Pi 3


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
