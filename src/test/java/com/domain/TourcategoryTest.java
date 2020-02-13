package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class TourcategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tourcategory.class);
        Tourcategory tourcategory1 = new Tourcategory();
        tourcategory1.setId(1L);
        Tourcategory tourcategory2 = new Tourcategory();
        tourcategory2.setId(tourcategory1.getId());
        assertThat(tourcategory1).isEqualTo(tourcategory2);
        tourcategory2.setId(2L);
        assertThat(tourcategory1).isNotEqualTo(tourcategory2);
        tourcategory1.setId(null);
        assertThat(tourcategory1).isNotEqualTo(tourcategory2);
    }
}
