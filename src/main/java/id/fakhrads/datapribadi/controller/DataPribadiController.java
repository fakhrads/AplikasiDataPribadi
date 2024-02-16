package id.fakhrads.datapribadi.controller;

import id.fakhrads.datapribadi.exception.DataPribadiExecption;
import id.fakhrads.datapribadi.model.DataPribadi;
import id.fakhrads.datapribadi.service.DataPribadiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class DataPribadiController {
    @Autowired private DataPribadiService service;
    public int hitungUmur(LocalDate tanggalLahir) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(tanggalLahir, today);
        return period.getYears();
    }
    @GetMapping("/")
    public String showMonitoringIndex(Model model) {
        List<DataPribadi> dataPribadiList = service.listAll();
        model.addAttribute("listData", dataPribadiList);
        return "index";
    }

    @GetMapping("/tambah")
    public String indexCreate(Model model) {
        model.addAttribute("pageTitle", "Tambah Data Baru");
        return "tambah";
    }

    @PostMapping("/edituser/simpan")
    public String createData(DataPribadi data, RedirectAttributes ra) {
        service.save(data);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
        return "redirect:/";
    }

    @PostMapping("/tambah/simpan")
    public String editSaveData(DataPribadi data, RedirectAttributes ra) {
        try {
            DataPribadi existingData = service.get(data.getNik());
            ra.addFlashAttribute("error", "NIK already exists. Please use a different one.");
        } catch (DataPribadiExecption ex) {
            service.save(data);
            ra.addFlashAttribute("message", "The user has been saved successfully.");
        }
        return "redirect:/";
    }

    @GetMapping("/view/{nik}")
    public String showViewForm(@PathVariable("nik") Long nik, Model model, RedirectAttributes ra) {
        try {
            DataPribadi data = service.get(nik);
            model.addAttribute("data", data);
            model.addAttribute("pageTitle", "View User (ID: " + nik + ")");

            return "view";
        } catch (DataPribadiExecption e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{nik}")
    public String showEditForm(@PathVariable("nik") Long nik, Model model, RedirectAttributes ra) {
        try {
            DataPribadi data = service.get(nik);
            model.addAttribute("data", data);
            model.addAttribute("pageTitle", "Edit User (ID: " + nik + ")");

            return "edit";
        } catch (DataPribadiExecption e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/delete/{nik}")
    public String deleteUser(@PathVariable("nik") Long nik, RedirectAttributes ra) {
        try {
            service.delete(nik);
            ra.addFlashAttribute("message", "The user ID " + nik + " has been deleted.");
        } catch (DataPribadiExecption e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/";
    }

}
