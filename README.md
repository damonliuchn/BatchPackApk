#BatchPackApk

BatchPackApk is a tool for batch package Android apk with channel string.

1.Don't need keystrore. 

2.Only use a signed apk. 

3.It can pack a large number apks in one second.

#Usage: 
1.You don't need to write channel value in AndroidManifest.xml,so if you want get the channel value in your app,please use following code.

For example,you use Umeng to analyse channel: 
```java
Umeng.setChannel(attainChannelFromMETAINF(context))
```
```java
private static String attainChannelFromMETAINF(Context context) {
        String result = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(context.getApplicationInfo().sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                if (entry.getName().startsWith("META-INF/channel")) {
                    result = entry.getName();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = result.split("-");
        if (split.length >= 3) {
            result = result.substring(split[0].length() + split[1].length() + 2);
            return result;
        } else {
            result = "Develop";
        }
        return result;
    }
```

2.package
```java
  example: 
  
  java -jar batchpackapk.jar example.apk ./ -c channel1,channel2

  introduction:
  java -jar batchpackapk.jar apk_path output_path [options]
  Options:
    -help, --help
       Default: false
    -c
       channel list string, Example:channel1,channel2
       Default: []
    -cf
       channel list file path,file content format:one line,one channel string
    -debug, -verbose
       Debug mode
       Default: false
```
-----
Blog:http://blog.csdn.net/masonblog

Email:MasonLiuChn@gmail.com
