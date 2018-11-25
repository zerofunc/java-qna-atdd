package nextstep.service;

import nextstep.CannotDeleteException;
import nextstep.CannotUpdateException;
import nextstep.UnAuthenticationException;
import nextstep.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import support.test.BaseTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest extends BaseTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QnaService qnaService;

    @Test
    public void update_equal_writer() throws UnAuthenticationException, CannotUpdateException {
        Question question1 = QuestionTest.QUESTION_1;
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        Question target = new Question(1L, "제목", "본문", UserTest.JAVAJIGI);
        softly.assertThat(qnaService.update(UserTest.JAVAJIGI, 1L, target)).isEqualTo(target);
    }

    @Test(expected = CannotUpdateException.class)
    public void update_not_equal_writer() throws CannotUpdateException {
        Question question = QuestionTest.QUESTION_1;
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Question target = new Question(1L,"제목", "본문", UserTest.SANJIGI);
        qnaService.update(UserTest.SANJIGI, 1L, target);
    }

    @Test
    public void deleteQuestion() throws CannotDeleteException {
        Question question = QuestionTest.QUESTION_1;
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        qnaService.deleteQuestion(UserTest.JAVAJIGI, question.getId());
    }

    @Test(expected = CannotDeleteException.class)
    public void deleteQuestion_not_equal_writer() throws CannotDeleteException {
        Question question1 = QuestionTest.QUESTION_1;
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question1));
        qnaService.deleteQuestion(UserTest.SANJIGI, question1.getId());
    }

    @Test
    public void addAnswer() {
    }

    @Test
    public void deleteAnswer() {
    }
}