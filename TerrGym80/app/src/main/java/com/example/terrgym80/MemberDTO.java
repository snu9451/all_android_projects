package com.example.terrgym80;

public class MemberDTO {
    public final String TAG = "MemberDTO";
    private String mem_id = null;
    private String mem_pw = null;
    private String mem_name = null;
    private String mem_email = null;

    public MemberDTO() {
        MemberDTO.getInstance();
    }

    public static MemberDTO getInstance() {
        return LazyHolder.instance;
    }

    public static class LazyHolder {
        private static final MemberDTO instance = new MemberDTO();
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public void removeInfo() {
        setMem_email(null);
        setMem_pw(null);
        setMem_id(null);
        setMem_name(null);
    }

}
