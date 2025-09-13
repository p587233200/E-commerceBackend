package com.cheng.ecommercebackend.controller;


import com.cheng.ecommercebackend.entity.User;
import com.cheng.ecommercebackend.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")  // 允許前端的來源
public class userController {
    // 模擬使用者清單
    private List<User> createUserList() {
        List<User> users = new ArrayList<>();
        users.add(new User(
                1,
                "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
                "admin",
                "111111",
                "平台管理员",
                Arrays.asList("平台管理员"),
                Arrays.asList("cuser.detail"),
                Arrays.asList("home"),
                "Admin Token"
        ));
        users.add(new User(
                2,
                "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
                "system",
                "111111",
                "系统管理员",
                Arrays.asList("系统管理员"),
                Arrays.asList("cuser.detail", "cuser.user"),
                Arrays.asList("home"),
                "System Token"
        ));
        return users;
    }

    // 用戶登入
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        User checkUser = createUserList().stream()
                .filter(u -> u.getName().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        Map<String, Object> result = new HashMap<>();
        if (checkUser == null) {
            result.put("code", 201);
            result.put("message", "帳號密碼不正確");
            result.put("ok", false);
            result.put("data", "帳號密碼不正確");
        } else {
            result.put("code", 200);
            result.put("message", "登入成功");
            result.put("ok", true);
            String token = JwtUtil.createToken(checkUser.getName());
            result.put("data", token);
        }
        return result;
    }

    // 取得用戶資訊
    @GetMapping("/info")
    public Map<String, Object> info(@RequestHeader("token") String token) {
        String username = JwtUtil.validateToken(token);

        Map<String, Object> result = new HashMap<>();
        if (username == null) {
            result.put("code", 201);
            result.put("message", "token 無效或過期");
            result.put("ok", false);
            result.put("data", Collections.singletonMap("message", "token 無效或過期"));
            return result;
        }

        User checkUser = createUserList().stream()
                .filter(u -> u.getName().equals(username))
                .findFirst()
                .orElse(null);

        if (checkUser == null) {
            result.put("code", 201);
            result.put("message", "獲取用户資訊失敗");
            result.put("data", Collections.singletonMap("message", "獲取用户資訊失敗"));
        } else {
            result.put("code", 200);
            result.put("data", checkUser);
        }
        return result;
    }

    // 登出
    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestHeader(value = "token", required = false) String token) {
        Map<String, Object> result = new HashMap<>();

        // 1. 沒有帶 token
        if (token == null || token.isEmpty()) {
            result.put("code", 200);
            result.put("message", "缺少 token，無法登出");
            return result;
        }

        // 2. token 已經在黑名單
        if (JwtUtil.containsInBlackList(token)) {
            result.put("code", 200);
            result.put("message", "登出失敗，token 已經在黑名單");
            return result;
        }

        // 3. 檢查 token 是否合法
        String username = JwtUtil.validateToken(token);
        if (username == null) {
            result.put("code", 200);
            result.put("message", "登出失敗，token 無效或過期");
            return result;
        }

        // 4. 加到黑名單 (模擬可能錯誤)
        try {
            JwtUtil.addToBlackList(token);
            result.put("code", 200);
            result.put("message", "登出成功，token 已失效");
            result.put("data", Collections.singletonMap("message", "登出成功，token 已失效"));
        } catch (Exception e) {
            result.put("code", 200);
            result.put("message", "登出失敗，server内部錯誤");
            result.put("data", Collections.singletonMap("message", "登出失败，server内部錯誤"));
        }

        return result;
    }

}
