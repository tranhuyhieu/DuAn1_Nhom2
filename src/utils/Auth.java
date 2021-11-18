
package utils;

import Entity.NhanVien;
import Entity.KhachHang;

public class Auth {
   
    public static NhanVien user1 = null;
    public static KhachHang user2 = null;
    
    public static void clear(){
        Auth.user1 = null;
        Auth.user2 = null;
    }
    
    public static boolean isLogin(){
        return Auth.user1 != null || Auth.user2 != null;
    }
    
    public static boolean isManager(){
        return Auth.isLogin() && user1.getVaiTro();
    }
}
