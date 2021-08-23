```
docker exec -it elasticsearch bash
cd bin
elasticsearch-plugin install analysis-nori
exit

// restart 필수
docker restart elasticsearch
```

## 참고자료
* https://esbook.kimjmin.net/06-text-analysis/6.7-stemming/6.7.2-nori
* http://kimjmin.net/2019/08/2019-08-how-to-analyze-korean/
* https://blog.jusun.org/archives/266