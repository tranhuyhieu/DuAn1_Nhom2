
package utils;

import entity.NhanVien;
import entity.KhachHang;

public class Auth {
   
    public static NhanVien user1 = null;
    public static KhachHang user2 = null;
    
    public static void clear(){
        Auth.user1 = null;
        Auth.user2 = null;
    }
    
    public static boolean isLogin(){
        return Auth.user1 != null && Auth.user2 != null;
    }
    
    public static boolean isManager(){
        return Auth.isLogin() && user1.getVaiTro();
    }
    
    public static int isCustomer(){
        if(isManager() == false){
            return 0;
        }else if(isManager() == true){
            return 1;
        }else if(Auth.isLogin()){
            return 2;
        }
        return 3;
    }
}