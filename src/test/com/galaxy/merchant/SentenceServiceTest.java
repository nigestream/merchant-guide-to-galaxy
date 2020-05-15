package test.com.galaxy.merchant;

import com.galaxy.merchant.entity.Sentence;
import com.galaxy.merchant.enums.SentenceType;
import com.galaxy.merchant.service.SentenceService;
import org.junit.Assert;
import org.junit.Test;

public class SentenceServiceTest {
    @Test
    public void testGetSentenceType() {
        Sentence sentence = new Sentence("how much is pish glob prok ?");
        Sentence sentence1 = new Sentence("how many is pish glob prok ?");
        Sentence sentence2 = new Sentence("glob prok Gold is 57800 Credits");
        Sentence sentence3 = new Sentence("tegj is M");
        Sentence sentence4 = new Sentence("how many Credits is glob prok Silver ?");


        Assert.assertEquals(SentenceType.QUESTION_HOW_MUCH, sentence.getSentenceType());
        Assert.assertEquals(SentenceType.NOMATCH, sentence1.getSentenceType());
        Assert.assertEquals(SentenceType.CREDITS, sentence2.getSentenceType());
        Assert.assertEquals(SentenceType.ASSIGNED, sentence3.getSentenceType());
        Assert.assertEquals(SentenceType.QUESTION_HOW_MANY, sentence4.getSentenceType());
    }
}
