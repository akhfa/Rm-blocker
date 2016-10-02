# Rm-blocker

## Penjelasan
Aplikasi ini adalah bagian dari repository https://github.com/akhfa/Dist_IDS. Program ini berfungsi untuk mendapatkan pesan dari rabbitmq, dan kemudian melakukan blocking menggunakan iptables.

## Cara kerja
Pada repository DistIDS, program ini dirancang untuk dijalankan sebagai daemon. Berikut adalah parameter program ini
1. Host
2. virtualhost
3. username
4. password
5. exchange name
6. queue name
7. routing key

Parameter-parameter tersebut adalah parameter dari server RabbitMQ yang digunakan.