package br.com.locaweb.relatorioclientes.controller;

import br.com.locaweb.relatorioclientes.service.ClienteService;
import br.com.locaweb.relatorioclientes.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RelatorioPdfController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/relatorio/pdf")
    public ResponseEntity<byte[]> gerarPdfResumido() {
        byte[] pdfBytes = pdfService.gerarRelatorioResumido(clienteService.getClientesAtivos());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_resumido.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
    
    @GetMapping("/relatorio/pdf/completo")
    public ResponseEntity<byte[]> gerarPdfCompleto() {
        byte[] pdfBytes = pdfService.gerarRelatorioCompleto(clienteService.getClientesAtivos());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_completo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    
    
}
