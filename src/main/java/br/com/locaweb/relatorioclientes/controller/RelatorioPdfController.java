package br.com.locaweb.relatorioclientes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.locaweb.relatorioclientes.model.ExecucaoManutencao;
import br.com.locaweb.relatorioclientes.repository.ExecucaoRepository;
import br.com.locaweb.relatorioclientes.service.ClienteService;
import br.com.locaweb.relatorioclientes.service.PdfService;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class RelatorioPdfController {

    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ExecucaoRepository execucaoRepository;

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
    
    @GetMapping("/execucao/{id}/pdf")
    public ResponseEntity<byte[]> gerarPdfPorExecucao(@PathVariable Long id) throws Exception {
        ExecucaoManutencao execucao = execucaoRepository.findById(id).orElseThrow();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);
        doc.open();

        doc.add(new Paragraph("Relatório de Execução #" + execucao.getId()));
        doc.add(new Paragraph("Cliente: " + execucao.getSolicitacaoManutencao().getCliente().getNomCliente()));
        doc.add(new Paragraph("Máquina: " + execucao.getProblema().getMaquina().getNom_maq()));
        doc.add(new Paragraph("Problema: " + execucao.getProblema().getDescricao()));
        doc.add(new Paragraph("Execução: " + execucao.getDescricao()));
        doc.add(new Paragraph("Técnico: " + execucao.getTecnico()));
        doc.add(new Paragraph("Data: " + execucao.getDataExecucao()));

        doc.close();

        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=execucao_" + id + ".pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(out.toByteArray());
    }

    
    
}
