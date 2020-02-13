package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class CategorydetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Categorydetails.class);
        Categorydetails categorydetails1 = new Categorydetails();
        categorydetails1.setId(1L);
        Categorydetails categorydetails2 = new Categorydetails();
        categorydetails2.setId(categorydetails1.getId());
        assertThat(categorydetails1).isEqualTo(categorydetails2);
        categorydetails2.setId(2L);
        assertThat(categorydetails1).isNotEqualTo(categorydetails2);
        categorydetails1.setId(null);
        assertThat(categorydetails1).isNotEqualTo(categorydetails2);
    }
}
