## PLEASE NOTE, THIS PROJECT IS NO LONGER BEING MAINTAINED,PLEASE GO TO https://github.com/Meituan-Dianping/walle

#BatchPackApk

BatchPackApk is a tool for batch package Android apk with channel string.

1.Don't need keystrore. 

2.Only provide a signed apk. 

3.It can pack a large number apks in one second.

4.The signature of generated apk is the same as the signature of you provided APK.

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
        } else {
            result = "Normal";
        }
        Log.i("channelFromMETAINF", result);
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

License
=======

    Copyright 2013 MasonLiu, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

