package com.cjc.cibil.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.cibil.app.servicei.CibilServiceI;

@RestController
@RequestMapping("/api/v2")
public class CibilController {

	@Autowired
	CibilServiceI csi;
}
