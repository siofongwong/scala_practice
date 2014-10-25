import scala.io.Source.{fromInputStream}
import java.net._
import scala.sys.SystemProperties
import scala.util.matching.Regex

object Test {

    val urlLink = "http://ichart.finance.yahoo.com/table.csv?s=GOOG";
    
    def main(args: Array[String]) {
        val sysProp = new SystemProperties()
        sysProp.put("http.proxyHost", "proxy-jf.intel.com")
        sysProp.put("http.proxyPort", "911")
        val result = data_fetcher(args(0))
        println(result);
        
    }
    
    def data_fetcher(seqUrl: String): String =  {
        val url = new URL( seqUrl )
        val urlCon = url.openConnection()
        urlCon.setConnectTimeout(5000)
        urlCon.setReadTimeout( 1000 )
        fromInputStream( urlCon.getInputStream ).getLines.mkString("\n")
    }
    
    def patternMatching(input: String, regPattern: String): String = {
        val pattern = new Regex(regPattern)
        (pattern findAllIn input).mkString("\n")
    }
}


