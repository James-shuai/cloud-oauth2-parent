//package com.mengxuegu.oauth2.server.service;
//
//import com.mengxuegu.base.result.MengxueguResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
//import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/authentication")
//public class RevokeTokenEndpoint {
//
//    @Autowired
//    private AuthorizationServerEndpointsConfiguration endpoints;
//        @Autowired
//    @Qualifier("consumerTokenServices")
//    ConsumerTokenServices consumerTokenServices;
//
//
//    @RequestMapping("/oauth/logOut")
//    public Object revokeToken(HttpServletRequest request) {
//        String access_token = request.getParameter("access_token");
//        if (consumerTokenServices.revokeToken(access_token)){
//            return MengxueguResult.ok("注销成功");
//        }else{
//            return MengxueguResult.build(403,"注销失败");
//        }
//    }
//
//
//    public boolean delete(String[] ck_ids) {
//        //执行逻辑删除操作
//        for (String id : ck_ids) {
//            ClientDetail detail=this.baseDao.get(ClientDetail.class, id);
//            //移除这个客户端创建的所有token
//            InMemoryTokenStore tokenStore=(InMemoryTokenStore) endpoints.getEndpointsConfigurer().getTokenStore();
//            Collection<OAuth2AccessToken> tokens=tokenStore.findTokensByClientId(detail.getClientName());
//            if(tokens!=null&&tokens.size()>0){
//                for(OAuth2AccessToken accessToken:tokens){
//                    tokenStore.removeAccessToken(accessToken);
//                }
//            }
//            //对客户端进行逻辑删除
//            detail.setDeleted(1);
//            this.baseDao.update(detail);
//        }
//        return true;
//    }
//
//
//}
