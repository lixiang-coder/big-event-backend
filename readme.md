## 大事件后端

介绍：项目主要实现了对用户注册登录，通过jwt令牌进行校验身份，登录成功后用户可以发表文章，管理文章分类，实现增删改查。项目采用阿里云OSS实现了文件上传。

## 技术栈

Springboot 3.X  + MySQL  + Redis + JDK17 + Mybatis + knif4j

## 启动项目

注意事项：
1. 将`sql`文件夹下的sql文件执行
2. 将`utils`文件夹下的AliOssUtil类中的`ENDPOINT`，`ACCESS_KEY_ID`，`SECRET_ACCESS_KEY`，`BUCKET_NAME`替换成你自己申请的，不替换不影响项目的启动，只是文件上传就无法实现
3. `application.yml`文件中的配置记得替换成你自己的配置
4. 运行项目之前启动本地Redis，并且确保依赖都拉好了