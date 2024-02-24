# eShop

#### _deployed at https://eshop-raviehasan.koyeb.app/_

#### _by Ravie Hasan Abud - 2206031864 - Advance Programming A_

<br>

<details>

<summary> Tutorial 3 </summary>

### Refleksi

1. Explain what principles you apply to your project!

- Single Responsibility Principle (SRP) -> Menghapus hubungan inheritance antara CarController dengan ProductController (CarController no longer extends ProductController). Hal ini karena keduanya memiliki fungsi yang berbeda dan tidak perlu dihubungkan dengan relasi inheritance.
- (Open-Closed Principle) OCP -> Perubahan data types untuk carService menjadi CarService interface (tidak lagi CarServiceImpl). Selain best practices untuk menggunakan interface sebagai data types, hal ini juga termasuk implementasi dari open closed principle karena open for extension and closed for modification.
- (Liskov Substitution Principle) LSP -> Mengubah CarController menjadi independent class dengan tidak menjadi subclass ProductController. Penghapusan hubungan inheritance ini juga sesuai dengan LSP.
- Interface Segregation Principle (ISP) -> Mengubah CarServiceImpl menjadi simply suatu interface CarService pada file CarController.java. Hal ini sesuai dengan ISP karena tidak ada interface yang irrelevant.
- (Dependency Inversion Principle) DIP -> Menggunakan CarService bukan CarServiceImpl sebagai placeholder data types. Hal ini sesuai dengan DIP karena mengabstraksi data rather than implementasi konkret dari CarServiceImpl untuk berperan pada car controller. 

2. Explain the advantages of applying SOLID principles to your project with examples.

Dengan mengimplementasikan SOLID principle, ktia dapat meingkatkan redability, maintanability, efficient, dan flexibility project. Dengan menerapkan SOLID principles, akan lebih mudah untuk memodifikasi project jika ada yang harus diubah atau mungkin ditambahkan karena project kita sudah maintanable. Selain itu, proses modifikasi project juga akan lebih cepat jika telah menerapkan SOLID principles. Contohnya adalah perubahan CarServiceImpl menjadi simply CarService interface pada car controller dapat mengabstraksi implementasi dari car service. Dengan demikian, project dapat menjadi lebih readable dan maintanable. Contoh lainnya adalah penghapusan hubungan inheritance pada CarController dengan ProductController untuk separation of concerns. Dengan adanya hal ini, ketika kita memodifikasi salah satu controller (baik car maupun product),tidak akan ada ripple effects karena saling independent.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.

Tanpa menggunakan SOLID principles, project dapat menjadi tidak flexible, fragile, stagnant. Selain itu, tingkat readability project juga menurun. Dengan demikian, project juga akan menjadi lebih sulit untuk dimaintain. Contohnya adalah jika CarController tetap extends (menjadi subclass) dari ProductController, tidak akan ada single responsibility principle. Dengan demikian, ketika kita hanya ingin mengubah ProductController, CarController juga akan terdampak perubahannya (karena ProductController adalah superclassnya). Selain itu, ketika kita hanya ingin mengubah salah satu controller saja, akan memakan waktu lebih lama karena saling terkait (lebih challenging untuk dimaintain), sedangkan sebenarnya kita dapat menerapkan separation of concerns pada kasus ini.

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