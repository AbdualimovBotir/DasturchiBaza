package com.example.dataservise.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DasturchiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String familiya;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String lavozim;
    @Column(nullable = false)
    private Integer maosh;

}
