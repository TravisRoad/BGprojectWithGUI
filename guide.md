# BGProject

## 软件功能

* 浏览市场上发布的各类桌游简介以及评分
* 搜索自己要玩的桌游
* 用户注册功能：注册之后进行游戏记录
* **记录功能**：记录游戏对战日期、局数、参与玩家和胜负情况
* 查看自己游玩的历史记录，和统计信息

## 开发环境

* Windows 10 Pro 2004，macOS 11.0
* IntelliJ IDEA 2020，Java 8 with JavaFX

## 界面设计

* 采用标签页设计，实现



## 软件模块划分

整体采用MVC架构![MVC](https://www.runoob.com/wp-content/uploads/2014/08/1200px-ModelViewControllerDiagram2.svg_.png)

- **模型（Model）** 用于封装与应用程序的业务逻辑相关的数据以及对数据的处理方法。
- **视图（View）**能够实现数据有目的的显示。
- **控制器（Controller）**起到不同层面间的组织作用，用于控制应用程序的流程。它处理事件并作出响应。“事件”包括用户的行为和数据 Model 上的改变。
- **Dao (Database Access Object)  **用于访问数据库的对象
- **资源 (resource)  **相关资源，例如描述页面风格的CSS文件，和图片资源
- **transport  ** 用于设计底层的通信，负责C-S通信
- **util  **各种实用工具类，比如加密，json解析，xml解析

## 数据库设计

数据库架设在阿里云服务器上

## 各模块功能解析

### controller
* 控制 GUI 上的控件

### dao（Database Access Object）

* 数据库交互对象

### resource
* 资源目录

### model
* 实体对象

### transport
* C-S 通信层

### util
* 有用的小零件

### view
* GUI 视图