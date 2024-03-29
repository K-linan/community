package life.majijang.community.controller;

import life.majijang.community.dto.AccessTokenDTO;
import life.majijang.community.dto.GithubUser;
import life.majijang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2019/12/23/0023.
 */
@Controller
public class AuthorizeController {
  @Value("${github.client.id}")
  private String client_id;
  @Value("${github.client.secret}")
  private String client_secret;
  @Value("${github.redirect.uri}")
  private String redirect_uri;
  @Autowired
  private GithubProvider githubProvider;
  @GetMapping("life/callback")
  public String callback(@RequestParam(name="code") String code,
                         @RequestParam(name="state") String state){
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
    accessTokenDTO.setClient_id(client_id);
    accessTokenDTO.setClient_secret(client_secret);
    accessTokenDTO.setCode(code);
    accessTokenDTO.setRedirect_uri(redirect_uri);
    accessTokenDTO.setState(state);
    String accessTokenDto = githubProvider.getAccessTokenDto(accessTokenDTO);
    GithubUser user = githubProvider.getUser(accessTokenDto);
    System.out.println(user.getName());
    return "index";
  }
}
