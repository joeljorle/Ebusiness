package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class CategorydetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategorydetailsDTO.class);
        CategorydetailsDTO categorydetailsDTO1 = new CategorydetailsDTO();
        categorydetailsDTO1.setId(1L);
        CategorydetailsDTO categorydetailsDTO2 = new CategorydetailsDTO();
        assertThat(categorydetailsDTO1).isNotEqualTo(categorydetailsDTO2);
        categorydetailsDTO2.setId(categorydetailsDTO1.getId());
        assertThat(categorydetailsDTO1).isEqualTo(categorydetailsDTO2);
        categorydetailsDTO2.setId(2L);
        assertThat(categorydetailsDTO1).isNotEqualTo(categorydetailsDTO2);
        categorydetailsDTO1.setId(null);
        assertThat(categorydetailsDTO1).isNotEqualTo(categorydetailsDTO2);
    }
}
