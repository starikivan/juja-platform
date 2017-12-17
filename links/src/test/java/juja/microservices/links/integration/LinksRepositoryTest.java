package juja.microservices.links.integration;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import juja.microservices.links.model.Link;
import juja.microservices.links.model.SaveLinkRequest;
import juja.microservices.links.repository.impl.LinksRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class LinksRepositoryTest extends BaseIntegrationTest {
    @Inject
    private LinksRepositoryImpl repository;

    @Test
    public void saveNewLinkTest() {
        String url = "http://test.com";
        Link result = repository.saveLink(new SaveLinkRequest(url));

        assertNotNull(result);
        assertThat(result.getId(), not(isEmptyString()));
        assertEquals(url, result.getURL());
    }

    @Test
    @UsingDataSet(locations = "/dataset/links.json")
    public void saveExistingLinkTest() {
        String url = "http://test.com";
        Link result = repository.saveLink(new SaveLinkRequest(url));

        assertNotNull(result);
        assertEquals(url, result.getURL());
        assertEquals("5a30508811d3b338a0b3f85c", result.getId());
    }
}