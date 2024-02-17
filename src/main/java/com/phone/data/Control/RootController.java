package com.phone.data.Control;

import com.phone.data.Entity.ReversalTable;
import com.phone.data.Impl.Billingimpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@RequestMapping("/file")
public class RootController {


    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @GetMapping("/mti")
    public String index(Model model) {
        ReversalTable courses = new ReversalTable();
        model.addAttribute("chartData", courses);
        return "index1";
    }

    @GetMapping("/myPage")
    public String getList(Model model) throws SQLException {
        ResultSet resultSet =  Billingimpl.getIntervenantById();
        model.addAttribute("items", Billingimpl.createList(resultSet)); //try block hidden for brevity
        return "myPage";
    }



//    private List<List<Object>> getChartData() {
//        return List.of(
//                List.of("Mushrooms", RANDOM.nextInt(5)),
//                List.of("Onions", RANDOM.nextInt(5)),
//                List.of("Olives", RANDOM.nextInt(5)),
//                List.of("Zucchini", RANDOM.nextInt(5)),
//                List.of("Pepperoni", RANDOM.nextInt(5))
//        );
//    }
//    @GetMapping
//    public String index1() {
//        return "index1";
//    }
}
