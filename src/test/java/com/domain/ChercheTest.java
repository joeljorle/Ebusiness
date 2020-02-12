package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class ChercheTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cherche.class);
        Cherche cherche1 = new Cherche();
        cherche1.setId(1L);
        Cherche cherche2 = new Cherche();
        cherche2.setId(cherche1.getId());
        assertThat(cherche1).isEqualTo(cherche2);
        cherche2.setId(2L);
        assertThat(cherche1).isNotEqualTo(cherche2);
        cherche1.setId(null);
        assertThat(cherche1).isNotEqualTo(cherche2);
    }
}
