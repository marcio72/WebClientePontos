package br.com.locaweb.relatorioclientes.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import br.com.locaweb.relatorioclientes.model.Cliente;
import br.com.locaweb.relatorioclientes.util.ConvertRegiao;

//import org.attoparser.dom.Document;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

@Service
public class PdfService {

    public byte[] gerarRelatorioResumido(List<Cliente> clientes) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Título
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            document.add(new Paragraph("Relatório de Clientes Ativos", tituloFont));
            document.add(new Paragraph(" "));

            // Data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            document.add(new Paragraph("Data de emissão: " + LocalDateTime.now().format(formatter)));
            document.add(new Paragraph("Total de clientes ativos: " + clientes.size()));
            document.add(new Paragraph(" "));

            // Contagem por Bairro
            Map<String, Integer> bairros = new TreeMap<>();
            for (Cliente cliente : clientes) {
                bairros.put(cliente.getBairro(), bairros.getOrDefault(cliente.getBairro(), 0) + 1);
            }
            document.add(new Paragraph("Resumo por Bairro:", tituloFont));
            for (Map.Entry<String, Integer> entry : bairros.entrySet()) {
                document.add(new Paragraph("- " + entry.getKey() + ": " + entry.getValue()));
            }
            document.add(new Paragraph(" "));

            // Contagem por Região
            Map<String, Integer> regioes = new TreeMap<>();
            for (Cliente cliente : clientes) {
                String nomeRegiao = ConvertRegiao.exibirNome(cliente.getRegiao());
                regioes.put(nomeRegiao, regioes.getOrDefault(nomeRegiao, 0) + 1);
            }
            document.add(new Paragraph("Resumo por Região:", tituloFont));
            for (Map.Entry<String, Integer> entry : regioes.entrySet()) {
                document.add(new Paragraph("- " + entry.getKey() + ": " + entry.getValue()));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
    
    
    public byte[] gerarRelatorioCompleto(List<Cliente> clientes) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            document.add(new Paragraph("Relatório Completo de Clientes", tituloFont));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(7); // 7 colunas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            // Cabeçalhos
            Stream.of("Código", "Nome", "Logradouro", "Telefone", "Bairro", "Região", "Data Cadastro")
                .forEach(col -> {
                    PdfPCell header = new PdfPCell(new Phrase(col));
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(header);
                });

            // Linhas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            for (Cliente c : clientes) {
                table.addCell(c.getNomCliente());
                table.addCell(c.getBairro());
                table.addCell(c.getTelefone());
                table.addCell(ConvertRegiao.exibirNome(c.getRegiao()));
                table.addCell(c.getDtCadastro().format(formatter)); // ✅ formatado
            }


            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

}
