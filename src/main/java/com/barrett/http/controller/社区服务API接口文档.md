# 社区服务 API 接口文档

## 基础相关信息

1. 服务器请求地址：http://47.115.83.135

   > 客户端访问统一接口规则 ： `/api/v2/`

   gitHub 完整 API 接口服务文档查阅：https://github.com/slimkit/slimkit.github.io/tree/gh-pages/docs

3. 体验版前台地址：http://47.115.83.135/feeds

4. 后台管理系统地址： http://47.115.83.135/admin

   > 体验账号：root
   >
   > 登陆密码：root




## 一、广告管理

### 1.1 查询所有广告位

```js
GET /advertisingspace
```

```json
Status: 200 OK
[
    {
        "id": 1,
        "channel": "boot", // 广告位所属模块
        "space": "boot", // 广告位标识
        "alias": "启动图广告", // 广告位别名
        "allow_type": "image", // 广告位允许的广告类型
        "format": { // 广告数据格式
            "image": {
                "image": "图片|string|表单描述",
                "link": "链接|string|表单描述"
            }
        },
        "created_at": "2017-07-27 06:56:36",
        "updated_at": "2017-07-27 06:56:36"
    },
    // 更多......
]
```



### 1.2 获取一个广告位的广告列表

```js
// space 代表 广告位 ID 参数信息
GET /advertisingspace/:space/advertising
```

```json
Status: 200 OK
[
    {
        "id": 1,
        "space_id": 1, // 广告位id
        "title": "广告1", // 广告标题
        "type": "image", // 广告类型
        "data": { // 广告数据
            "image": "http://plus.bai/api/v2/files/1", // 广告图片地址
            "link": "http://www.baidu.com" // 广告链接
        },
        "sort": 2,
        "created_at": "2017-07-27 15:09:15",
        "updated_at": "2017-07-27 15:09:16"
    }
]
```



### 1.3 批量获取广告列表

```js
GET /advertisingspace/advertising?space=1,2,3
```

| 名称  | 类型   | 描述                     |
| ----- | ------ | ------------------------ |
| space | 字符串 | 广告位id，多个以逗号隔开 |

```json
Status: 200 OK
[
    {
        "id": 1,
        "space_id": 1, // 广告位id
        "title": "广告1", // 广告标题
        "type": "image", // 广告类型
        "data": { // 广告数据
            "image": "http://plus.bai/api/v2/files/1", // 广告图片地址
            "link": "http://www.baidu.com" // 广告链接
        },
        "sort": 2,
        "created_at": "2017-07-27 15:09:15",
        "updated_at": "2017-07-27 15:09:16"
    }
]
```



## 二、动态管理

### 2.1 批量获取动态列表信息

```js
GET /feeds
```

|  名称  |         类型          | 描述                                                         |
| :----: | :-------------------: | ------------------------------------------------------------ |
| limit  |        Integer        | 可选，默认值 20 ，获取条数                                   |
| after  |        Integer        | 可选，上次获取到数据最后一条 ID，用于获取该 ID 之后的数据。  |
|  type  |        String         | 可选，默认值 new，可选值 `new` 、`hot` 、 `follow` 、`users` |
| search |        String         | type = `new`时可选，搜索关键字                               |
|  user  |        Integer        | type = `users` 时可选，默认值为当前用户id                    |
| screen |        string         | type = `users` 时可选，`paid`-付费动态 `pinned` - 置顶动态   |
|  `id`  | `integer` or `string` | **可选**，按照动态 ID 获取动态列表。                         |
| `hot`  |       `integer`       | **可选，仅 `type=hot` 时有效**，用于热门数据翻页标记！上次获取数据最后一条的 `hot` 值 |

```json
{
    "ad": null,
    "pinned": [...], // 置顶动态列表
    "feeds": [
        {
            "id": 1,
            "user_id": 1,
            "feed_content": "12312312312",
            "feed_from": 1,
            "like_count": 0,
            "feed_view_count": 4,
            "feed_comment_count": 3,
            "feed_latitude": "",
            "feed_longtitude": "",
            "feed_geohash": "",
            "audit_status": 1,
            "feed_mark": 12312312,
            "pinned": 1,
            "pinned_amount": 0,
            "created_at": "2017-08-01 16:46:19",
            "updated_at": "2017-08-05 03:29:55",
            "deleted_at": null,
            "comments": [
                {
                    "id": 4,
                    "user_id": 1,
                    "target_user": 1,
                    "reply_user": 0,
                    "body": "辣鸡啊啊啊啊",
                    "commentable_id": 1,
                    "commentable_type": "feeds",
                    "created_at": "2017-08-05 03:29:55",
                    "updated_at": "2017-08-05 03:29:55",
                    "pinned": true
                }
            ],
            "has_collect": false,
            "has_like": false,
            "images": [],
            "paid_node": null,
            "topics": [ // 话题数据
                {
                    "id": 1,            // 话题 ID
                    "name": "第一个话题" // 话题名称
                }
            ]
        }
    ]
}
```

|         名称         |  类型   | 描述                                               |
| :------------------: | :-----: | -------------------------------------------------- |
|          id          |   int   | 动态数据id                                         |
|       user_id        |   int   | 发布者id                                           |
|     feed_content     | string  | 动态内容                                           |
|      feed_from       |   int   | 动态来源 1:pc 2:h5 3:ios 4:android 5:其他          |
|      like_count      |   int   | 点赞数                                             |
|   feed_view_count    |   int   | 查看数                                             |
|  feed_comment_count  |   int   | 评论数                                             |
|    feed_latitude     | string  | 纬度                                               |
|   feed_longtitude    | string  | 经度                                               |
|     feed_geohash     | string  | GEO                                                |
|     audit_status     |   int   | 审核状态                                           |
|      feed_mark       |   int   | 标记                                               |
|        pinned        |   int   | 置顶标记                                           |
|    pinned_amount     |   int   | 置顶金额                                           |
|       comments       |  array  | 动态评论 列表中返回五条                            |
|     comments.id      |   int   | 评论id                                             |
|   comments.user_id   |   int   | 评论者id                                           |
| comments.target_user |   int   | 资源作者id                                         |
| comments.reply_user  |   int   | 被回复者id                                         |
|    comments.body     | string  | 评论内容                                           |
|   comments.pinned    |  bool   | 评论置顶标记 不存在则为普通评论                    |
|     has_collect      |  bool   | 是否已收藏                                         |
|       has_like       |  bool   | 是否已赞                                           |
|        images        |  array  | 图片信息 同单条动态数据结构一致                    |
|      paid_node       |  array  | 付费节点信息 同单条动态数据结构一致 不存在时为null |
|       `topics`       | `Array` | 参考「动态详情」，内容一致。                       |



### 2.2 获取指定 ID 动态详情

```js
GET /feeds/:feed
```

```json
{
    "id": 13,
    "created_at": "2017-06-21 01:54:52",
    "updated_at": "2017-06-21 01:54:52",
    "deleted_at": null,
    "user_id": 1, // 发布动态的用户
    "feed_content": "动态内容", // 内容
    "feed_from": 2,
    "like_count": 0, // 点赞数
    "feed_view_count": 0, // 查看数
    "feed_comment_count": 0, // 评论数
    "feed_latitude": null, //  纬度
    "feed_longtitude": null, // 经度
    "feed_geohash": null, // GeoHash
    "audit_status": 1, // 审核状态
    "feed_mark": 12,
    "has_like": true, // 是否点赞
    "has_collect": false, // 用户是否收藏当前动态
    "paid_node": {
        "paid": true, // 当前用户是否已经付费
        "node": 9, // 付费节点
        "amount": 20 // 付费金额
    },
    "comment_paid_node": { // 评论收费信息.
        "paid": true,
        "node": 11,
        "amount": 50
    },
    "reward": {
        "count": 3, // 被打赏次数
        "amount": "600" // 被打赏总金额
    },
    "images": [ // 图片
        {
            "file": 4, // 文件 file_with 标识 不收费图片只存在 file 这一个字段。
            "size": null, // 图像尺寸，非图片为 null，图片没有尺寸也为 null，
            "amount": 100, // 收费多少
            "type": "download", // 收费方式
            "paid": false, // 当前用户是否购买
            "paid_node": 10 付费节点
        },
        {
            "file": 5,
            "size": '1930x1930' // 当图片有尺寸的时候采用 width x height 格式返回。
        }
    ],
    "likes": [
        {
            "id": 2,
            "user_id": 1,
            "target_user": 1,
            "likeable_id": 1,
            "likeable_type": "feeds",
            "created_at": "2017-07-12 08:09:07",
            "updated_at": "2017-07-12 08:09:07"
        }
    ],
    "topics": [ // 话题数据
        {
            "id": 1,            // 话题 ID
            "name": "第一个话题" // 话题名称
        }
    ],
    "repostable_type": null, // 转发资源类型
    "repostable_id": null,   // 转发资源 ID
}
```



### 2.3 创建一条动态

```js
POST /feeds
```

| Name              | Type      | Description                                                  |
| ----------------- | --------- | ------------------------------------------------------------ |
| feed_content      | string    | 分享内容。**如果存在附件，则为可选，否则必须存在**           |
| feed_from         | integer   | 客户端标识，1-PC、2-Wap、3-iOS、4-android、5-其他            |
| feed_mark         | mixed     | 客户端请求唯一标识                                           |
| feed_latitude     | string    | 纬度，**当经度， GeoHash 任意一个存在，则本字段必须存在**    |
| feed_longtitude   | string    | 经度，**当纬度， GeoHash 任意一个存在，则本字段必须存在**    |
| feed_geohash      | string    | GeoHash，**当纬度、经度 任意一个存在，则本字段必须存在**     |
| amount            | inteter   | 动态收费，**不存在表示不收费，存在表示收费。**               |
| images            | array     | 结构：`{ id: <id>, amount: <amount>, type: <read,download> }`，**amount 为可选，id 必须存在，amount 为收费金额，单位分, type 为收费方式** |
| `topics`          | `Array`   | **可选**，需要关联的话题 ID 数组。                           |
| `topics.*`        | `integer` | **如果 `topics` 存在则必须**，话题 ID。                      |
| `repostable_type` | `string`  | **可选，如果 `repostable_id` 存在则必须**，转发资源类型标识。 |
| `repostable_id`   | `integer` | **可选，如果 `repostable_type` 存在则必须**，转发资源 ID。   |

```json
{
    "feed_content": "内容",
    "feed_from": "5",
    "feed_mark": "xxxxx1",
    "images": [
        {
            "id": 1
        },
        {
            "id": 1
            "amount": 100,
            "type": "read"
        }
    ],
    "feed_latitude": "12.32132123",
    "feed_longtitude": "32.33332123",
    "feed_geohash": "GdUDHyfghjd==",
    "amount": 450,
    "topics": [1, 2, 3]
}
```

```js
Status: 201 Created
{
    "message": [
        "发布成功"
    ],
    "id": 1
}
```



### 2.4 点赞、取消点赞、点赞列表 当前动态

**点赞**

```js
POST /feeds/:feed/like
```

```
Status: 201 Created
{
    "message": [
        "操作成功"
    ]
}
```



**取消点赞**

```js
DELETE /feeds/:feed/unlike
```

```
Status: 204 Not Content
```



**点赞人列表**

```
GET /feeds/:feed/likes
```

| 名称  | 类型    | 描述                      |
| ----- | ------- | ------------------------- |
| limit | Integer | 获取条数，默认 20         |
| after | Integer | `id` 获取之后数据，默认 0 |

```
Status: 200 OK
[
    {
        "id": 3,
        "user_id": 2,
        "target_user": 1,
        "likeable_id": 1,
        "likeable_type": "feeds",
        "created_at": "2017-07-12 08:09:07",
        "updated_at": null,
        "user": {
            "id": 2,
            "name": "test1",
            "bio": "0",
            "sex": 0,
            "location": "0",
            "created_at": "2017-06-12 07:38:55",
            "updated_at": "2017-06-12 07:38:55",
            "following": true,
            "follower": false,
            "avatar": null,
            "bg": null,
            "verified": null,
            "extra": null
        }
    }
]
```

| 字段          | 描述                                              |
| ------------- | ------------------------------------------------- |
| id            | 喜欢 ID                                           |
| user_id       | 点喜欢用户 ID                                     |
| target_user   | 接收喜欢用户 ID                                   |
| likeable_id   | 喜欢的资源 ID，配置 `likeable_type` 表示不同资源  |
| likeable_type | 喜欢的资源类型。                                  |
| created_at    | 点喜欢时间                                        |
| updated_at    | 更新时间                                          |
| user          | 点喜欢的用户资料，结构参考 「用户信息」接口说明。 |



### 2.5 评论指定 ID 动态

**发布一条评论**

```
POST /feeds/:feed/comments
```

| Name       | Type    | Description |
| ---------- | ------- | ----------- |
| body       | String  | 评论内容    |
| reply_user | Integer | 评论用户 ID |

```js
Status: 201 Created
{
    "message": [
        "操作成功"
    ],
    "comment": {
        "user_id": 1,
        "reply_user": 0,
        "target_user": 1,
        "body": "我是第三条评论",
        "commentable_type": "feeds",
        "commentable_id": 1,
        "updated_at": "2017-07-20 08:53:24",
        "created_at": "2017-07-20 08:53:24",
        "id": 3
    }
}
```



获取当前动态所有评论

```
GET /feeds/:feed/comments
```

| Name  | Type    | Description                      |
| ----- | ------- | -------------------------------- |
| limit | Integer | 单次请求条数，默认为20           |
| after | Integer | 最后一条评论的 ID 用来做请求区分 |

```js
Status: 200 OK
{
    "pinneds": [
        {
            "id": 2,
            "user_id": 1,
            "target_user": 1,
            "reply_user": 0,
            "body": "我是第一条评论",
            "commentable_id": 1,
            "commentable_type": "feeds",
            "created_at": "2017-07-20 08:35:18",
            "updated_at": "2017-07-20 08:35:18"
        }
    ],
    "comments": [
        {
            "id": 3,
            "user_id": 1,
            "target_user": 1,
            "reply_user": 0,
            "body": "我是第三条评论",
            "commentable_id": 1,
            "commentable_type": "feeds",
            "created_at": "2017-07-20 08:53:24",
            "updated_at": "2017-07-20 08:53:24"
        }
    ]
}
```

| Name               | Description                |
| ------------------ | -------------------------- |
| pinneds            | Pinned comments list.      |
| comment            | Comments list.             |
| *.id               | The `ID` of the comment.   |
| *.user_id          | Commentator.               |
| *.target_user      | Own dynamic publisher.     |
| *.reply_user       | Reverted to the user.      |
| *.body             | The `body` of the comment. |
| *.commentable_id   | Feeds id.                  |
| *.commentable_type | Commentable type.          |
| *.created_at       | Comment release time.      |
| *.updated_at       | Comment update time.       |



**获取当前一条评论的详细信息**

```
GET /feeds/:feed/comments/:comment
```

```js
Status: 200 OK
{
    "id": 1,
    "user_id": 1,
    "target_user": 1,
    "reply_user": 0,
    "body": "我是第一条评论",
    "commentable_id": 1,
    "commentable_type": "feeds",
    "created_at": "2017-07-20 08:34:41",
    "updated_at": "2017-07-20 08:34:41"
}
```

| Name             | Description                |
| ---------------- | -------------------------- |
| id               | The `ID` of the comment.   |
| user_id          | Commentator.               |
| target_user      | Own dynamic publisher.     |
| reply_user       | Reverted to the user.      |
| body             | The `body` of the comment. |
| commentable_id   | Feeds id.                  |
| commentable_type | Commentable type.          |
| created_at       | Comment release time.      |
| updated_at       | Comment update time.       |



**删除一条评论**

```
DELETE /feeds/:feed/comments/:comment
```

```
Status: 204 No Content
```





### 2.6 删除指定 ID动态

``````js
DELETE /feeds/:feed/currency
``````



### 2.7 收藏、取消收藏当前动态

**收藏**

```
POST /feeds/:feed/collections
```

```
Status: 201 Created
{
    "message": [
        "收藏成功"
    ]
}
```



**取消收藏**

```
DELETE /feeds/:feed/uncollect
```

```
Status: 204 No Centent
```



**收藏列表**

```
GET /feeds/collections
```

| 名称   | 类型    | 描述                                           |
| ------ | ------- | ---------------------------------------------- |
| limit  | Integer | 可选，默认值 20 ，获取条数                     |
| offset | Integer | 可选，偏移量，用于翻页，传入已请求过的数据总数 |
| user   | Integer | type = `users` 时可选，默认值为当前用户id      |

```js
Status: 200 OK
[
    {...}  // 数据参考动态单条内容
]
```



### 2.8 举报一个动态

```
POST /feeds/:feed/reports
```

| 名称   | 描述     |
| ------ | -------- |
| reason | 举报理由 |

```json
Http Status 201
{
    "message": [
        "操作成功"
    ]
}
```



## 三、资讯管理

### 3.1 批量获取资讯列表信息

```
GET /news
```

| 名称      | 说明                                                 |
| --------- | ---------------------------------------------------- |
| limit     | 数据返回条数 默认为20                                |
| after     | 数据翻页标识                                         |
| key       | 搜索关键字                                           |
| cate_id   | 分类id                                               |
| recommend | 推荐筛选 =1为筛选推荐资讯列表                        |
| `id`      | 需要按照 ID 获取的资讯 ID，多个使用半角 `,` 进行分割 |

```json
Status: 200 OK
[
    {
        "id": 2,
        "title": "asdasdasdad",
        "subject": "阿拉拉啊拉",
        "created_at": "2017-08-02 03:28:38",
        "updated_at": "2017-08-02 09:08:51",
        "from": "原创",
        "author": "root",
        "user_id": 1,
        "hits": 10,
        "has_collect": false,
        "has_like": false,
        "category": {
            "id": 1,
            "name": "1121",
            "rank": 0
        },
        "image": {
            "id": 2,
            "size": "370x370"
        }
    },
    {
        "id": 1,
        "title": "123",
        "subject": "0",
        "created_at": "2017-07-31 11:56:32",
        "updated_at": "2017-07-31 06:31:31",
        "from": "原创",
        "author": "root",
        "user_id": 1,
        "hits": 5,
        "has_collect": false,
        "has_like": false,
        "category": {
            "id": 1,
            "name": "1121",
            "rank": 0
        },
        "image": {
            "id": 1,
            "size": "100x100"
        }
    }
]
```

| 名称          | 类型       | 说明                                    |
| ------------- | ---------- | --------------------------------------- |
| id            | int        | 数据id                                  |
| title         | string     | 资讯标题                                |
| subject       | string     | 副标题                                  |
| from          | string     | 来源                                    |
| author        | string     | 作者                                    |
| user_id       | int        | 发布者id                                |
| hits          | int        | 点击数                                  |
| has_collect   | bool       | 当前用户是否已收藏                      |
| has_like      | bool       | 当前用户是否已点赞                      |
| category      | array      | 所属分类信息                            |
| category.id   | int        | 所属分类id                              |
| category.name | string     | 所属分类名称                            |
| category.rank | int        | 所属分类排序                            |
| image         | array/null | 资讯封面信息 为null时表示该资讯无缩略图 |
| image.id      | int        | 资讯封面附件id                          |
| image.size    | string     | 资讯封面尺寸                            |



### 3.2 获取指定 ID 资讯详情信息

```
GET /news/{news}
```

```json
Status: 200 OK
{
    "id": 2,
    "created_at": "2017-08-02 03:28:38",
    "updated_at": "2017-08-02 09:08:51",
    "title": "asdasdasdad",
    "content": "@![辣鸡啊](3) adasd image @![辣鸡啊](4)",
    "digg_count": 0,
    "comment_count": 0,
    "hits": 4,
    "from": "原创",
    "is_recommend": 0,
    "subject": "阿拉拉啊拉",
    "author": "root",
    "audit_status": 0,
    "audit_count": 0,
    "user_id": 1,
    "contribute_amount": 100,
    "has_collect": false,
    "has_like": false,
    "is_pinned": false,
    "category": {
        "id": 1,
        "name": "1121",
        "rank": 0
    },
    "image": {
        "id": 2,
        "size": "370x370"
    },
    "tags": [
        {
            "id": 2,
            "name": "标签2",
            "tag_category_id": 1
        },
        {
            "id": 1,
            "name": "标签1",
            "tag_category_id": 1
        }
    ]
}
```

| 名称                 | 类型   | 说明                                                   |
| -------------------- | ------ | ------------------------------------------------------ |
| id                   | int    | 资讯id                                                 |
| title                | string | 标题                                                   |
| content              | text   | 资讯内容 markdown格式 含自定义标签()                   |
| digg_count           | int    | 点赞数                                                 |
| comment_count        | int    | 评论数                                                 |
| hits                 | int    | 点击量                                                 |
| from                 | string | 来源                                                   |
| is_recommend         | int    | 是否为推荐                                             |
| subject              | string | 副标题                                                 |
| author               | string | 作者                                                   |
| audit_status         | int    | 审核状态 0-正常 1-待审核 2-草稿 3-驳回 4-删除 5-退款中 |
| audit_count          | int    | 审核驳回次数                                           |
| user_id              | int    | 发布者id                                               |
| contribute_amount    | int    | 投稿金额                                               |
| has_collect          | bool   | 当前用户是否已收藏                                     |
| has_like             | bool   | 当前用户是否已点赞                                     |
| is_pinned            | bool   | 是否已置顶 1-已置顶                                    |
| category             | array  | 所属分类信息                                           |
| category.id          | int    | 所属分类id                                             |
| category.name        | string | 所属分类名称                                           |
| category.rank        | int    | 所属分类排序                                           |
| image                | array  | 资讯封面信息                                           |
| image.id             | int    | 资讯封面附件id                                         |
| image.size           | string | 资讯封面尺寸                                           |
| tags                 | array  | 标签列表                                               |
| tags.id              | int    | 标签id                                                 |
| tags.name            | string | 标签名称                                               |
| tags.tag_category_id | int    | 标签分类id                                             |



### 3.3 点赞、取消点赞、点赞列表当前资讯

**喜欢资讯**

```
POST /news/{news}/likes
```

```
Status: 201 Created
```



**取消喜欢资讯**

```
DELETE /news/{news}/likes
```

```
Status: 204 No Content
```



**资讯喜欢列表**

```
GET /news/{news}/likes
```

```json
Status: 200 Ok
[
    {
        "id": 5,
        "user_id": 1,
        "target_user": 1,
        "likeable_id": 4,
        "likeable_type": "news",
        "created_at": "2017-08-08 02:25:34",
        "updated_at": "2017-08-08 02:25:34",
        "user": {
            "id": 1,
            "name": "baishi",
            "bio": null,
            "sex": 1,
            "location": null,
            "created_at": "2017-07-31 03:16:19",
            "updated_at": "2017-08-09 10:09:28",
            "following": false,
            "follower": false,
            "avatar": null,
            "bg": null,
            "verified": null,
            "extra": {
                "user_id": 1,
                "likes_count": 2,
                "comments_count": 9,
                "followers_count": 0,
                "followings_count": 0,
                "updated_at": "2017-08-17 07:05:06",
                "feeds_count": 0,
                "questions_count": 0,
                "answers_count": 19
            }
        }
    }
]
```

| 名称        | 类型  | 说明         |
| ----------- | ----- | ------------ |
| user_id     | int   | 喜欢用户id   |
| target_user | int   | 被喜欢用户id |
| likeable_id | int   | 被喜欢资源id |
| created_at  | date  | 喜欢时间     |
| user        | array | 用户信息     |



### 3.4 评论指定 ID 资讯

**评论一条资讯**

```
POST /news/{news}/comments
```

| 名称       | 描述                 |
| ---------- | -------------------- |
| body       | 评论内容             |
| reply_user | 被回复用户id 默认为0 |

```json
Status: 201 Created
{
    "message": [
        "操作成功"
    ],
    "comment": {
        "user_id": 1,
        "reply_user": 0,
        "target_user": 1,
        "body": "baishi",
        "commentable_type": "news",
        "commentable_id": 2,
        "updated_at": "2017-08-10 09:31:58",
        "created_at": "2017-08-10 09:31:58",
        "id": 5
    }
}
```

| 名称                | 描述         |
| ------------------- | ------------ |
| message             | 消息         |
| comment             | 评论信息     |
| comment.id          | 评论id       |
| comment.user_id     | 评论者id     |
| comment.target_user | 资讯发布者id |
| comment.reply_user  | 被回复者id   |
| comment.body        | 评论内容     |



**获取一条资讯的评论列表**

```
GET /news/{news}/comments
```

| 名称  | 类型 | 必须 | 说明         |
| ----- | ---- | ---- | ------------ |
| limit | int  | -    | 数据返回条数 |
| after | int  | -    | 数据翻页标识 |

```json
Status: 200 OK
{
    "pinneds": [],
    "comments": [
        {
            "id": 2389,
            "user_id": 215,
            "target_user": 0,
            "reply_user": 0,
            "created_at": "2017-07-12 01:13:45",
            "updated_at": "2017-07-12 01:13:45",
            "commentable_type": "news",
            "commentable_id": 31,
            "body": "而且其实我想把这篇资讯分享到我的动态，然而……"
        },
        {
            "id": 2388,
            "user_id": 215,
            "target_user": 0,
            "reply_user": 0,
            "created_at": "2017-07-12 01:13:01",
            "updated_at": "2017-07-12 01:13:01",
            "commentable_type": "news",
            "commentable_id": 31,
            "body": "我下拉到底部后，评论编辑窗口不见了……，"
        }
    ]
}
```

| 名称        | 描述         |
| ----------- | ------------ |
| pinneds     | 置顶评论     |
| comments    | 评论列表     |
| id          | 评论id       |
| user_id     | 评论者id     |
| target_user | 资讯发布者id |
| reply_user  | 被回复者id   |
| body        | 评论内容     |



**删除一条资讯评论**

```
DELETE /news/{news}/comments/{comment}
```

```json
Status: 204 No Content
```



### 3.5 收藏、取消收藏资讯信息

**收藏资讯**

```
POST /news/{news}/collections
```

```
Status: 201 Created
```



**取消收藏资讯**

```
DELETE /news/{news}/collections
```

```
Status: 204 No Content
```



**获取收藏资讯**

```
GET /news/collections
```

| 名称  | 类型 | 必填 | 说明         |
| ----- | ---- | ---- | ------------ |
| limit | int  | -    | 数据返回条数 |
| after | int  | -    | 数据翻页标识 |

```json
Status: 200 Ok
[
    {
        "id": 2,
        "title": "asdasdasdad",
        "subject": "阿拉拉啊拉",
        "created_at": "2017-08-02 03:28:38",
        "updated_at": "2017-08-03 07:53:34",
        "from": "原创",
        "author": "root",
        "user_id": 1,
        "hits": 5,
        "has_collect": true,
        "has_like": false,
        "category": {
            "id": 1,
            "name": "1121",
            "rank": 0
        },
        "image": {
            "id": 2,
            "size": "370x370"
        }
    }
]
```

| 名称          | 类型   | 说明               |
| ------------- | ------ | ------------------ |
| id            | int    | 数据id             |
| title         | string | 资讯标题           |
| subject       | string | 副标题             |
| from          | string | 来源               |
| author        | string | 作者               |
| user_id       | int    | 发布者id           |
| hits          | int    | 点击数             |
| has_collect   | bool   | 当前用户是否已收藏 |
| has_like      | bool   | 当前用户是否已点赞 |
| category      | array  | 所属分类信息       |
| category.id   | int    | 所属分类id         |
| category.name | string | 所属分类名称       |
| category.rank | int    | 所属分类排序       |
| image         | array  | 资讯封面信息       |
| image.id      | int    | 资讯封面附件id     |
| image.size    | string | 资讯封面尺寸       |



### 3.6 举报一个资讯

```
POST /news/:news/reports
```

| 名称   | 描述     |
| ------ | -------- |
| reason | 举报理由 |

```json
Http Status 201
{
    "message": [
        "操作成功"
    ]
}
```



## 四、用户管理

### 4.1 用户注册 - 图形验证码

> PS: 使用该图形验证码的目的是为了防止 短信、邮件 恶意注册
>
> 在这里验证码验证的过程掌握逻辑即可

````html
<image :src=" 'http://47.115.83.135/passport/captcha/' + Math.random() " />
````



### 4.2 用户注册 - 获取验证码

> PS：使用验证码的目的是为了确保用户注册电话或者邮箱的唯一性，防止用户恶意注册
>
> 发送验证码支持 `email` 和 `phone`

非注册用户发送：

```
POST /verifycodes/register
```

注册用户发送：

```
POST /verifycodes
```

| 名称  | 类型   | 描述                                                         |
| ----- | ------ | ------------------------------------------------------------ |
| phone | String | **如果 `email` 不存在则必须**，以 `sms` 模式给手机发送验证码。 |
| email | String | **如果 `phone` 不存在则必须**，以 `mail` 模式给邮箱发送验证码。 |

```
Status: 202 Accepted
{
    "message": [
        "获取成功"
    ]
}
```



### 4.3 用户注册 - 提交注册信息

```
POST /users
```

| 名称            | 类型                  | 描述                                                       |
| --------------- | --------------------- | ---------------------------------------------------------- |
| name            | 字符串                | **必须**，用户名                                           |
| phone           | 字符串                | **如果 `verifiable_type` 为 `sms` 则必须**, 手机号码。     |
| email           | String                | **如果 `verifiable_type` 为 `mail` 则必须**, E-Mail。      |
| password        | String                | **可选**，密码，如果不输入密码，允许用户无密码注册。       |
| verifiable_type | 枚举: `mail` 或 `sms` | **必须**，验证码发送模式。                                 |
| verifiable_code | 字符串或数字          | **必须**，用户收到的验证码。(教学阶段验证码统一使用 8888 ) |

```json
Status: 201 Created
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6Ly9wbHVzLmlvL2FwaS92Mi90b2tlbnMiLCJpYXQiOjE1MDAzNjU5MzQsImV4cCI6MTUwMTU3NTUzNCwibmJmIjoxNTAwMzY1OTM0LCJqdGkiOiJ1aXlvdTQwNnJsdU9pa3l3In0.OTM4mbH3QW7busunRsFUsheE5vysuIfrBrwjWnd0J6k",
    "ttl": 20160,
    "refresh_ttl": 40320
}
```



### 4.4 用户登陆

```
POST /auth/login
```

| 名称              | 类型     | 描述                                                         |
| ----------------- | -------- | ------------------------------------------------------------ |
| login             | 字符串   | **必须**，用户认证字段，可以是 `name` 、 `email` 和 `phone` 。 |
| password          | 字符串   | **可选**，用户密码。                                         |
| `verifiable_code` | `string` | **可选**，登录验证码。                                       |

> `password` 或者 `verifiable_code` 必须选择一个，如果选择 `verifiable_code` 进行登录。那么 `login` 字段只能是 `phone` 或者 `email`。

```
Status: 20o OK
{
    "access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vcGx1cy5pby9hcGkvdjIvYXV0aC9sb2dpbiIsImlhdCI6MTUxNTU3NDE0MSwiZXhwIjoxNTE1NTc3NzQxLCJuYmYiOjE1MTU1NzQxNDEsImp0aSI6Imx1MWtFcDd1UjZpWnoxV3giLCJzdWIiOjEsInBydiI6IjQ4ZTQ1MzgzMWNlYmE1ZTU3YTQ3NWU2ODY0OWNmZGVlNmU5N2Q4ZDIifQ.0_u1dgb-rSr2o7nIx4Q1n1NNcr1LMAtgTbKsFFdUvmg",
    "token_type": "bearer",
    "expires_in": 60,
    "refresh_ttl": 20160
}
```

| 字段         | 描述                 |
| ------------ | -------------------- |
| access_token | 授权 Token           |
| token_type   | Token 类型           |
| expires_in   | 过期时间，单位分     |
| refresh_ttl  | 刷新过期时间，单位分 |

### 4.5 用户退出

```
POST|PUT|PATCH|GET /auth/logout
```

> 需要 `认证`

```
Status: 200 OK
{
    "message": "退出成功"
}
```



### 4.6 刷新当前用户 Token

```
POST|PUT|PATCH|GET /auth/refresh
```

> 需要 `认证`



### 4.7 获取当前登录用户未读消息

**Unread message count（未读消息统计）**

```
GET /user/counts
```

```
Status: 200 OK
{
    "user": { // 用户相关
        "following": 1, // 用户关注者新增（粉丝新增）数量
        "liked": 0,     // 被点赞数
        "commented": 0, // 被评论数
        "system": 0,    // 系统消息,
        "news-comment-pinned": 0, // 置顶评论消息
        "feed-comment-pinned": 0, // 动态置顶消息
        "mutual": 0,    // 其他消息
        "at": 2,        // At 我的消息
    }
}
```



**At me（@我的）**

```
GET /user/message/atme
```

| Name        | Type      | Description                                                  |
| ----------- | --------- | ------------------------------------------------------------ |
| `limit`     | `integer` | **可选**，请求的数据条数，默认 `15`，允许范围 `1 - 100`。    |
| `index`     | `integer` | **可选**，数据开始查找的位置标记，默认 `0`，来源响应体的 `id` 字段。 |
| `direction` | `enum`    | **可选**，以 `index` 标记开始的数据顺序，默认 `desc`，允许值：`asc`/`desc`。 |

```json
Status: 200 OK
[
    {
        "id": 1,               // 消息 ID
        "user_id": 1,          // 消息接收人ID（当前用户ID）
        "resourceable": {      // 资源
            "type": "feeds",   // 资源类型
            "id": 1            // 资源ID
        },
        "created_at": "2018-08-13T08:06:54Z" // 消息创建时间
    }
]
```



### 4.8 获取当前登陆用户签到相关信息

**获取签到信息**

```
GET /user/checkin
```

```json
Status: 200 OK
{
    "rank_users": [
        {
            "id": 1,
            "name": "Seven",
            "bio": "Seven 的个人传记",
            "sex": 2,
            "location": "成都 中国",
            "created_at": "2017-06-02 08:43:54",
            "updated_at": "2017-07-25 03:59:39",
            "avatar": "http://plus.io/api/v2/users/1/avatar",
            "bg": "http://plus.io/storage/user-bg/000/000/000/01.png",
            "verified": null,
            "extra": {
                "user_id": 1,
                "likes_count": 0,
                "comments_count": 8,
                "followers_count": 0,
                "followings_count": 1,
                "updated_at": "2017-08-11 01:32:36",
                "feeds_count": 0,
                "questions_count": 5,
                "answers_count": 3,
                "checkin_count": 2,
                "last_checkin_count": 2
            }
        }
    ],
    "checked_in": true,
    "checkin_count": 2,
    "last_checkin_count": 2,
    "attach_balance": 0
}
```

| 字段               | 描述                                                         |
| ------------------ | ------------------------------------------------------------ |
| rank_users         | 当日前五签到用户，按照签到时间顺序排列。（参考「用户资料」接口文档） |
| checked_in         | 当前用户是否已签到。                                         |
| checkin_count      | 当前用户签到总天数。                                         |
| last_checkin_count | 当前用户连续签到天数。                                       |
| attach_balance     | 签到用户积分增加值，单位是真实货币「分」单位。               |



**签到**

```
PUT /user/checkin
```

```
Status: 204 No Content
```



**累计签到排行榜**

```
GET /checkin-ranks
```

| 名称   | 类型    | 描述                     |
| ------ | ------- | ------------------------ |
| offset | Integer | 数据偏移数，默认为 `0`。 |
| limit  | Integer | 查询数据条数             |

```
Status: 200 OK
[
    {
        "id": 1,
        "name": "Seven",
        "bio": "Seven 的个人传记",
        "sex": 2,
        "location": "成都 中国",
        "created_at": "2017-06-02 08:43:54",
        "updated_at": "2017-07-25 03:59:39",
        "follwing": false,
        "follower": false,
        "avatar": "http://plus.io/api/v2/users/1/avatar",
        "bg": "http://plus.io/storage/user-bg/000/000/000/01.png",
        "verified": null,
        "extra": {
            "user_id": 1,
            "likes_count": 0,
            "comments_count": 8,
            "followers_count": 0,
            "followings_count": 1,
            "updated_at": "2017-08-11 01:32:36",
            "feeds_count": 0,
            "questions_count": 5,
            "answers_count": 3,
            "checkin_count": 2,
            "last_checkin_count": 2
        }
    },
    {
        "id": 2,
        "name": "test1",
        "bio": null,
        "sex": 0,
        "location": "0",
        "created_at": "2017-06-12 07:38:55",
        "updated_at": "2017-06-12 07:38:55",
        "follwing": true,
        "follower": false,
        "avatar": null,
        "bg": null,
        "verified": null,
        "extra": {
            "user_id": 2,
            "likes_count": 0,
            "comments_count": 0,
            "followers_count": 0,
            "followings_count": 0,
            "updated_at": null,
            "feeds_count": 0,
            "questions_count": 0,
            "answers_count": 0,
            "checkin_count": 0,
            "last_checkin_count": 0
        }
    }
]
```



### 4.9 获取一个用户

1. 获取指定用户：

```
GET /users/:user
```

| 变量 | 描述 |
| `user` | 这个变量可以是「用户名」、「邮箱」、「手机号码」和「用户ID」 |

|   参数    |  类型   | 描述                                                         |
| :-------: | :-----: | ------------------------------------------------------------ |
| following | Integer | 检查请求用户是否关注了指定的用户，传递要检查的用户 ID，默认为当前登录用户。 |
| follower  | Integer | 检查请求用户是否被某个用户关注，传递要检查的用户 ID，默认为当前登录用户。 |

2. 获取当前认证用户

```
GET /user
```

```
Status: 200 OK
```

```json5
{
    "id": 1,                           // 用户 ID
    "name": "创始人",                   // 用户名
    "phone": "18364758373",            // 用户手机号码，仅获取自己资料
    "email": "shiweidu@outlook.com",   // 用户邮箱，仅获取自己资料
    "bio": "我是大管理员",               // 用户描述
    "sex": 0,                          // 用户性别，1 男，2 女，0 未知
    "location": "成都市 四川省 中国",     // 用户地区
    "created_at": "2017-06-02 08:43:54",// 用户注册时间
    "updated_at": "2017-07-06 07:04:06",// 用户最后一次更新资料时间
    "avatar": {                        // 用户头像
        "vendor": "local",
        "url": "https://xxxxx",
        "mize": "image/png",
        "size": 8674535,
        "dimension": {
            "width": 240,
            "height": 240,
        }
    },
    "bg": null,                         // 同 `avatar` 字段
    "extra": {                          // 用户拓展信息
        "user_id": 1,
        "likes_count": 0,               // 喜欢数量
        "comments_count": 0,            // 评论数量
        "followers_count": 0,           // 关注者数量
        "followings_count": 1,          // 关注的人数量
        "updated_at": "2017-07-16 09:44:25",
        "feeds_count": 0                 // 动态数量
    },
    "wallet": {                          // 钱包信息，仅获取自己资料存在
        "id": 1,
        "user_id": 1,
        "balance": 90,                   // 钱包余额
        "created_at": "2017-06-02 08:43:54",
        "updated_at": "2017-07-05 08:29:49",
        "deleted_at": null
    },    
    "new_wallet": {                      // 钱包信息，仅获取自己资料存在
        "owner_id": 1,
        "balance": 1000,                 // 钱包余额
        "total_income": 100,             // 收入统计
        "total_expenses": 100,           // 支出统计
        "created_at": "2018-01-22 10:05:44",
        "updated_at": "2018-01-22 10:05:45"
    },
    "currency": {                        // 积分信息，仅获取自己资料存在
        "owner_id": 1,
        "type": 1,                       // 积分类型
        "sum": 9400,                     // 积分数量
        "created_at": "2018-01-17 06:57:18",
        "updated_at": "2018-01-18 06:57:24"
    }
}
```



### 4.10 更新认证用户资料

- [更新认证用户的手机号码和邮箱](#更新认证用户的手机号码和邮箱)
- [更新认证用户密码](#更新认证用户密码)

```
PATCH /user
```

|   字段   |            类型             | 描述                                  |
| :------: | :-------------------------: | ------------------------------------- |
|   name   |           字符串            | 用户新的用户名。                      |
|   bio    |           字符串            | 用户新的个人传记。                    |
|   sex    |            数字             | 用户新的性别。                        |
| location |           字符串            | 用户新的位置信息。                    |
| `avatar` | `FILE_STORAGE_NODE<string>` | **可选**，用户头像的 File node 值     |
|   `bg`   | `FILE_STORAGE_NODE<string>` | **可选**，用户背景图片的 File node 值 |

```
Status: 204 No Content
```

1. 更新认证用户的手机号码和邮箱

```
PUT /user
```

|      字段       |      类型      | 描述                                              |
| :-------------: | :------------: | ------------------------------------------------- |
|      phone      |     字符串     | **如果 `email` 不存在则必须**，用户新的手机号码。 |
|      email      |     字符串     | **如果 `phone` 不存在则必须**，用户新的邮箱地址。 |
| verifiable_code | 字符串或者数字 | **必须**，验证码。                                |

```
Status: 204 No Content
```

2. 更新认证用户密码

```
PUT /user/password
```

|         名称          |  类型  | 描述                                             |
| :-------------------: | :----: | ------------------------------------------------ |
|     old_password      | 字符串 | **用户已设置密码时必须**，用户密码。             |
|       password        | 字符串 | **必须**，用户的新密码                           |
| password_confirmation | 字符串 | **必须**，用户的新密码，必须和 `password` 一致。 |

```
Status: 204 No Content
```



### 4.11 获取所有用户

```
GET /users
```

|    名称    |         类型          | 描述                                                         |
| :--------: | :-------------------: | ------------------------------------------------------------ |
|  `limit`   |       `integer`       | **可选**，请求获取的数据量，默认为 `20` 条，最低获取 `1` 条，最多获取 `50` 条。 |
|  `order`   |       `string`        | **可选**，排序方式，默认 `desc`，可选 `asc` 或 `desc`。      |
|  `since`   |       `integer`       | **可选**，上次请求的最后一条的 `id` ，用于获取这个用户之后的数据。 |
|   `name`   |       `string`        | **可选**用于检索包含 `name` 传递字符串用户名的用户；如果 `fetch_by` 是 `username` 那么这里就是完整的用户名，多个用户名使用 `,` 进行分割。 |
| `fetch_by` |       `string`        | **可选**，获取数据的方式，默认是 `id` 已常规方式进行获取，允许值：`username` 使用 `name` 字段进行按照用户名获取、`id` 使用 `id` 字段按照用户 ID 进行获取。 |
|    `id`    | `integer` or `string` | **可选**，获取一个或者多个指定的用户，如果获取多个请使用 `,` 将用户 ID进行字符串拼接。 |

```
Status: 200 OK
```

```json
[
    {
        "id": 1,
        "name": "创始人",
        "bio": "我是大管理员",
        "sex": 0,
        "location": "成都市 四川省 中国",
        "created_at": "2017-06-02 08:43:54",
        "updated_at": "2017-07-06 07:04:06",
        "following": false,
        "follower": false,
        "avatar": "http://plus.io/api/v2/users/1/avatar",
        "bg": null,
        "extra": {
            "user_id": 1,
            "likes_count": 0,
            "comments_count": 0,
            "followers_count": 0,
            "followings_count": 1,
            "updated_at": "2017-07-16 09:44:25",
            "feeds_count": 0
        }
    }
]
```



### 4.12 用户找回密码

```
PUT /user/retrieve-password
```

|      名称       |          类型           | 描述                                                         |
| :-------------: | :---------------------: | ------------------------------------------------------------ |
| verifiable_type | 枚举：`mail` 或者 `sms` | **必须**，验证码发送模式。                                   |
| verifiable_code |     字符串或者整数      | **必须**，用户收到的验证码。                                 |
|      email      |         字符串          | 如果 `verifiable_type` 值为 `mail`，那么这个字段为必须，用户邮箱。 |
|      phone      |         字符串          | 如果 `verifiable_type` 值为 `sms`。那么这个字段为必须，用户手机号码。 |
|    password     |         字符串          | 用户新密码。                                                 |

```
Status: 204 No Content
```



### 4.13 解除用户 Phone 或者 E-Mail 绑定

解除用户 Phone 绑定:

```
DELETE /api/v2/user/phone
```

解除用户 E-Mail 绑定:

```
DELETE /api/v2/user/email
```

|      名称       |      类型       | 描述                     |
| :-------------: | :-------------: | ------------------------ |
|    password     |     String      | 用户密码。               |
| verifiable_code | Int 或者 String | 手机号码或者邮箱验证码。 |

```
Status: 204 No Content
```





## 五、文件上传管理

### 5.1 获取文件 hash 信息

> PS：通过 微信 API 文件读取接口读取文件 `filePath`

`````json
let hash = await new Promise((resolve, reject) => {
    uni.getFileInfo({
      filePath,
      success: (result) => {
        resolve(result.digest)
      }
    })
})
`````



### 5.2 文件上传检查

> PS：依赖上一步获取到的 `hash` 值

```js
GET /files/uploaded/`hash`
```



### 5.3 文件上传

```
POST /files
```

| 名称 | 类型 | 描述         |
| ---- | ---- | ------------ |
| file | File | 待上传的文件 |

```
Status: 201 Created
{
    "message": [
        "上传成功"
    ],
    "id": 1
}
```

`````json
let forms = new FormData()
forms.append('file',uni.getFileSystemManager().readFileSync(`filePath`))
				
await uni.request({
		url: 'http://47.115.83.135/api/v2/files',
		method: "POST",
		forms,
		headers: {
			'Content-Type': 'multipart/form-data',
      'Accept': "application/json",
      'Authorization': "Bearer " + uni.getStorageSync("accessToken").data
		},
})
`````



### 5.4 文件获取

```
GET /files/:file
```

> 这里的 :file 虽然叫做文件，实际是系统中的 file_with 标识，例如上传返回的都是 file_with 标识。





