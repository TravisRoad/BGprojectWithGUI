
# BGproject
## 功能
- 个人爱好资料库
- 引入外部资料库
- 记事本用于记录个人评论
- 观看（游玩）记录生成
- 时间轴
- 数据分析
- wordCloud
## 时间安排
## 数据库
### E-R
[链接](https://lucid.app/lucidchart/invitations/accept/035a8073-fa27-4ab6-8f76-bdb147db79f7)
### schemas
user(<u>user_id</u>,username,passwd);

play_history(<u>ph_id</u>,victory_player_id);

boardgame(<u>bg_id</u>,bg_name,introduction);

comment(<u>cm_id</u>,cm_content,rate);

play(<u>user_id,ph_id</u>);

game_played(<u>bg_id,ph_id</u>);

comment_game(<u>bg_id,cm_id</u>);

user_comment(<u>user_id,cm_id</u>);
