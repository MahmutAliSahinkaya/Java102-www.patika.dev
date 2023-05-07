Projenin ayağa kaldırılması ve kullanılması
============================
1. Java Development Kit (JDK) yüklü olduğundan emin olun. JDK'nın en az Java 8 veya daha yeni bir sürümü gerekmektedir.

2. Server ve client kodlarını ayrı dosyalara Server.java ve Client.java şeklinde kaydedin.

3. İlk olarak sunucuyu başlatın:

   - Bir komut clientını (terminal veya komut istemi) açın.
   - Server kodunun bulunduğu dizine (klasöre) gidin.
   - Aşağıdaki komutu kullanarak sunucuyu derleyin ve çalıştırın:
     - javac Server.java 
     - java Server
   - Server başarılı bir şekilde başlatıldığında "Server started on port 12345" gibi bir çıktı alacaksınız. Server şimdi belirtilen port üzerinden bağlantıları dinlemeye hazır olacaktır.

4. İkinci olarak clientı başlatın (bir veya daha fazla client oluşturabilirsiniz):
   - Yeni bir komut istemcisini açın (aynı sunucu çalışırken birden fazla client başlatmak için ayrı komut istemcileri kullanabilirsiniz).
   - Client kodunun bulunduğu dizine gidin.
   - Aşağıdaki komutu kullanarak clienti derleyin ve çalıştırın:
     - javac Client.java
     - java Client
   - Client başarılı bir şekilde başlatıldığında size bir kullanıcı adı girmenizi isteyecektir. Kullanıcı adını girin ve enter tuşuna basın.
   - Şimdi sohbete katıldınız ve diğer kullanıcıların mesajlarını görebilirsiniz. Kendi mesajlarınızı girmek için klavyeden yazıp enter tuşuna basabilirsiniz. "exit" yazarak sohbetten çıkabilirsiniz.

