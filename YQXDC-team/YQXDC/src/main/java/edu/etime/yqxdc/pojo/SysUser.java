package edu.etime.yqxdc.pojo;

public class SysUser {
    private String userid;

    private String roleid;

    private String username;

    private String userpwd;

    private String usersex;

    private String usertruename;

    private Integer userstate;
    //userState的字符串表现格式
    private String userStateStr;
    //userSex的字符串表现格式
    private String userSexStr;
    public String getUserStateStr() {
        if(userstate !=null && !userstate.equals("")){
            if(userstate == 1){

                userStateStr = "可用";
            }else{
                userStateStr = "不可用";
            }
        }else{
            userStateStr = "";
        }
        return userStateStr;
    }


    public String getUserSexStr() {
        if(usersex !=null && !usersex.equals("")){
            if(usersex.equals("1")){
                userSexStr = "男";
            }else{
                userSexStr="女";
            }
        }else{
            userSexStr = "";
        }
        return userSexStr;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex == null ? null : usersex.trim();
    }

    public String getUsertruename() {
        return usertruename;
    }

    public void setUsertruename(String usertruename) {
        this.usertruename = usertruename == null ? null : usertruename.trim();
    }

    public Integer getUserstate() {
        return userstate;
    }

    public void setUserstate(Integer userstate) {
        this.userstate = userstate;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "userid='" + userid + '\'' +
                ", roleid='" + roleid + '\'' +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", usersex='" + usersex + '\'' +
                ", usertruename='" + usertruename + '\'' +
                ", userstate=" + userstate +
                '}';
    }
}