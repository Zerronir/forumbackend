package com.tokendemo.tokendemo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("category")
public class Category {
    String title, description, slug, color;
    @Id
    int id;
}
