/**
*
* @author 
* Maftun Hashimli maftun.hashimli@ogr.sakarya.edu.tr
  Muhammed Mustafa Çelik   muhammed.celik6@ogr.sakarya.edu.tr

* @since 07/03/2020
* <p>
*dosyayı okuyup içerisinde bulunan kelimeleri, sesli
harfleri, cümleleri, eposta adreslerini ve web adreslerinin kaç adet olduğunu
ekrana yazan konsol uygulamasi
* </p>
*/
package pdp1odev;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;     //Kutuphanelerimi ekledik
import java.util.regex.Pattern;

/**
 *
 * @author MAFTUN
 */
public class Islem {
      public static void oku() throws FileNotFoundException, IOException {
          //Text dosyamizi okumak icin bufferedreader kullaniyoruz.
          //BufferedReadari gerektigi gibi kullanmamiz icin,assagda gordugunuz 3 kod blogunu kullanmaliyiz.Bu sayede,dosyayi line by line okuya biliriz 
        FileInputStream fileStream = new FileInputStream("icerik.txt");
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);
        String line;
        int countWord = 0;
        int sentenceCount = 0;//Degiskenlerimi olusturduk
        int mailCount = 0;
        int websitecounteer = 0;
        String[] wordList;
        String[] words;
        int vowelcount = 0;
        //Satiri bos olmaayana kadar oku diyoruz
        while ((line = reader.readLine()) != null) {
            //Gereksiz bosluklardan kurtulmak icin trim fonskiyonu kullaniyoruz
            String linetrim=line.trim();
            //Regexlerimizin,patternlerini yaziyoruz.Eklentileri |(or) yardimiyla ve bitisi $ yardimiyla yapiyoruz
            String websitepattern = "^(http://|https://)?(www.)?[a-zA-Z0-9-]+(.com$|.edu$|.com.tr$|.net$|.org$|.edu.tr$)+$";
          //tablara gore ayir diyoruz.Kelime saymada isimize yarayacak
            wordList = linetrim.split("\\s+"); 
            //Bu da mail icin gerekli olan pattern. $ lar bitisi, ^ ondan once bisey olamayacagini | ya da demektir
            String pattern = "[a-zA-Z0-9_.-]+@[a-zA-z0-9-]+\\.([a-zA-Z0-9-]|.[a-zA-Z0-9-])+[ |  |   ]?+$";
            //bosluklara gore boluyoruz
            words = linetrim.split(" ");
            //simdi o bolduklerimi bir dongu icinde islemlerin yapilmasi icin hazirliyorz
            for (int i = 0; i < words.length; i++) {
                //Burda yukarda tanimladigim patternine uygun olan kelime var mi onu kontrol ediyioruz
                boolean isMatch = Pattern.matches(pattern, words[i]);
                if (isMatch == true) {
                    //eger varsa arrtir
                    mailCount++;
                }
              //Burda yukarda tanimladigim patternine uygun olan kelime var mi onu kontrol ediyioruz
                boolean isMatched = Pattern.matches(websitepattern, words[i]);
                if (isMatched == true) {
                     //eger varsa arrtir
                    websitecounteer++;
                }
                //eger sonu . ile bitiyorsa cumledir
                if (words[i].endsWith(".")) {
                  //arttir  
                    sentenceCount++;
                }
                //harf okumak icin bir for dongusu daha aciyoruz.
                for (int j = 0; j < words[i].length(); j++) {
                    //ch eger kelimenin icinde if yapisindakilerden biri kullaniliyorsa
                    char ch = words[i].charAt(j);
                    if (ch == 'a' || ch == 'ı' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'ö' || ch == 'ü' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Ö' || ch == 'Ü' || ch == 'İ') //Checking for vowels
                    {
                        //arttir
                      vowelcount++;
                    }
                }
            }
            //hani kelimelere bolmustuk ya ve onu //s+ yardimiyla alt alta yazdik.Simdi onlari saymamiz yeterli.
            countWord += wordList.length;   
        }
        //Simdi yazdiriyoruz
        System.out.println("Toplam Sesli Harf Sayisi :" + vowelcount);
        System.out.println("Toplam kelime sayisi :" + countWord);
        System.out.println("Toplam cumle sayisi :" + sentenceCount);
        System.out.println("Toplam mail adresi sayisi :" + mailCount);
        System.out.println("Toplam web adresi sayisi :" + websitecounteer);
    }
}
