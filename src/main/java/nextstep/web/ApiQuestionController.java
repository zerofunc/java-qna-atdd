package nextstep.web;


import nextstep.domain.Question;
import nextstep.domain.User;
import nextstep.security.LoginUser;
import nextstep.service.QnaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/questions")
public class ApiQuestionController {
	@Resource(name ="qnaService")
	private QnaService qnaService;
	
	@PostMapping("")
	public ResponseEntity<Void> create(@LoginUser User loginUser, @Valid @RequestBody Question question) {
		Question savedQuestion = qnaService.create(loginUser, question);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/api/questions/"+savedQuestion.getId()));
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
