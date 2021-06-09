package com.hyy.ylog.controller;


import com.hyy.ylog.common.enums.LogType;
import com.hyy.ylog.common.note.YLogNote;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyy
 * @since 2020-10-10
 */
@RestController
@RequestMapping("/ylog")
public class YlogController {


    @GetMapping("/test/{id}")
    @YLogNote(type = LogType.SELECT)
    public String test(@PathVariable("id")Integer id){
        System.out.println(id);
        return "success";
    }
}

