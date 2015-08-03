#BatchPackApk

BatchPackApk_v1 a tool for batch package Android apk with channel string.

Don't need keystrore. Only use a signed apk.

Example:java -jar batchpackapk.jar example.apk ./ -c channel1,channel2

http://tech.meituan.com/mt-apk-packaging.html

#Usage: 
1 java -jar batchpackapk.jar apk_path output_path [options]
```java
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
2 get Channel code
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
-----
Blog:http://blog.csdn.net/masonblog

Email:MasonLiuChn@gmail.com
