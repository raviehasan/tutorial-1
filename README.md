# eShop

#### _deployed at https://eshop-raviehasan.koyeb.app/_

#### _by Ravie Hasan Abud - 2206031864 - Advance Programming A_

<br>

<details>

<summary> Tutorial 1 </summary>

### Refleksi 1

Pada tutorial pertama ini, saya sudah menerapkan banyak prinsiples dari clean code. Yang pertama adalah meaningful names, seperti `editedProduct` untuk membedakan dengan produk asli sebelum diedit dan `newQuantity` untuk menandakan atribut baru saat user edit produk. Selain itu, saya juga menerapkan prinsip small function that only do 1 thing dan menggunakan nama fungsi yang deskriptif, seperti `editProductPut` yang menandakan method ini menggunakan method PUT. Hal ini berbeda dengan method `editProductPage` yang sesuai namanya berfungsi untuk mengakses page tempat user memberikan input atribut baru produk. Saya juga sempat menulis beberapa potongan kode yang kurang straight forward, pada akhirnya, saya memutuskan untuk rewrite kode tersebut agar tidak perlu memberikan komentar karena sesuai principles dari clean coding, yaitu "comments do not make up for bad code". Selain itu, saya juga menyadari apabila kita memberikan komen, kemudian kita perlu mengubah kode kita pada bagian tersebut, sering kali kita lupa mengganti komennya yang menyebabkan komen dengan yang dilakukan kode tidak selaras (comments can be misleading). Kemudian saya juga menerapkan secure coding, yaitu melakukan validasi input quantity yang dimasukan user saat edit product. Hal yang saya validasi adalah jika input yang diberikan bernilai negatif (jika inputnya negatif, akan diasumsikan sebagai 0). Walaupun menurut saya tutorial 1 ini tergolong sulit, saya dapat mempelajari banyak hal dari tutorial ini.

### Refleksi 2

Setelah membuat unit test, saya merasa lebih tenang karena tahu kode saya berjalan sesuai dengan yang saya inginkan. Sepertinya tidak ada cara formal terkait bagaimana memastikan unit test sudah cukup untuk verifikasi program kita, tetapi kita bisa memikirkan segala kemungkinan input, error, dan apa saja fungsi yang akan dilakukan program kita, seberapa kompleks fungsinya, kemudian jika ada conditional statements mungkin bisa dibuat testingnya juga untuk setiap posiibilites. Menurut saya, 100% code coverage pada hasil test bukan necessarily berarti kode kita sudah berjalan perfect 100% no bugs no error. Hal ini tergantung dengan test apa yang diterapkan, bisa saja unit test yang diterapkan hanya memastikan semua url dapat berfungsi dengan template html yang sesuai. Pada kasus ini, memang coveragenya akan besar, tapi apakah logic dari program terjamin benar? Tentu saja tidak, bisa saja ada logic yang masih salah, tetapi karena memang testnya tidak mencapai bagian fungsionalitas, maka semua test dengan 100% coverage tersebut tetap passed.

Saya rasa, jika functional test suite baru yang dibuat sudah tepat, tidak akan membuat code logic jadi salah. Namun, jika dilihat dari cleanliness code test tersebut, rawan tidak menerapkan principles of clean code. Sesuai deskripsi soal, fungsi yang dilakukan oleh test ini cukup mirip dengan test sebelumnya, sehingga kemungkinan besar akan terdapat banyak duplikasi kode. Untuk menjaga cleanliness dari code agar tetap readable dan tidak redundant, kita dapat parameterize suatu method agar dapat mewakilkan beberapa fungsi yang mirip. Setelah selesai menyusun kode, kita dapat melakukan refactoring untuk mereview bagian-bagian yang dirasa kurang straight forward. Kode-kode yang kurang optimized dapat dibuat lebih clean agar lebih readable dan mudah untuk dimaintain ketika melakukan perubahan pada program kedepannya.

</details>

<br>

<details>

<summary> Tutorial 2 </summary>

### Refleksi

1. Code quality issue yang saya solve:

Terdapat public classes yang seharusnya perlu untuk dijadikan public, yaitu CreateProductFunctionalTest.java, HomePageFunctionalTest.java, ProductTest.java, dan ProductRepositoryTest.java. Ini termasuk pada issue "Intentionality". Strategi saya untuk solve masalah ini adalah simply menghapus modifier "public" (membiarkannya dengan default modifier). Selain itu, pada ProductList.html ada table yang tidak diberikan keterangan (consistency and reliability issue). Strategi saya untuk solve ini adalah dengan menambahkan html tag "caption" untuk memperjelas fungsi table apa. Di samping itu, sempat ada problem pada @Autowired di file ProductController.java dan ProductServiceImpl.java, tetapi setelah saya cek, saya rasa tidak bermasalah. Strategi saya untuk solve hal ini adalah dengan menandainya sebagai false positive (pada sonar cloud). Di samping code quality issue yang saya address berdasarkan info dari sonar cloud, saya juga membuat code saya semakin clean dengan menghapus beberaepa unused import dan merapihkan whitespace dari code saya agar lebih readable.

2. CI/CD

Jika dilihat dari segi workflows, saya sudah menerapkan ci.yml, scorecard.yml, dan sonarcloud.yml. Dengan demikian, setiap kali saya upload perubahan ke github, secara otomatis github actions akan menjalankan unit test yang telah saya definisikan, sehingga tidak akan mengganggu logic dan fungsionalitas aplikasi. Selain fungsionalitas, code quality juga akan diperhatikan oleh sonar cloud setiap saya melakukan perubahan. Dengan demikian, kode saya akan terhindar dari berbagai issue yang mungkin muncul (contohnya seperti di poin 1). scorecard.yml juga akan menganalisis keamanan aplikasi. Selain itu, saya juga menggunakan Koyeb untuk deployment, tentunya koyeb juga akan memeriksa aplikasi saya saat proses deploy. Jika terdapat permasalahan, akan terlihat dan dapat langsung saya solve. Oleh karena itu, menurut saya aplikasi ini telah memenuhi definisi Continuous Integration dan Continuous Deployment karena setiap kali ada perubahan pada aplikasi, fungsionalitas dan code quality akan selalu diperiksa, kemudian akan berjalan proses deployment untuk versi terbaru.

Terkait challenge/bonus, testing telah mencapai 100% code coverage.

</details>
