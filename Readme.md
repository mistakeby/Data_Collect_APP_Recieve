# Readme

## 简介：

Data_Collect_APP的配套后台接收端代码

Springboot+Servlet+FileUpload+mybatisPlus+MYSQL

## 实现功能：

接收表单文字数据

存储数据

接收图片

接收视频

## 流程：

图片和视频是按照指定规则存储在部署服务器上

存储规则为：“指定路径/”+“发送方的用户id/"+"指定路径"+”数据发送时间.jpg或.mp4“

文字数据存入数据库，同时将图片和视频的存储路径一并存入数据库，以便数据关联

