package br.com.locaweb.relatorioclientes.controller;

import br.com.locaweb.relatorioclientes.model.Cliente;
import br.com.locaweb.relatorioclientes.model.HistoricoAlteracao;
import br.com.locaweb.relatorioclientes.repository.ClienteRepository;
import br.com.locaweb.relatorioclientes.repository.HistoricoAlteracaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;



@Controller
@RequestMapping("/clientes")
public class ClienteCrudController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/novo")
    public String novoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario-cliente";
    }
    
    

    /*@PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        cliente.setNomCliente(cliente.getNomCliente().toUpperCase());

        if (cliente.getDtCadastro() == null) {
            cliente.setDtCadastro(LocalDateTime.now());
        }
        cliente.setAtivo(true);
        clienteRepository.save(cliente);

        redirectAttributes.addFlashAttribute("mensagem", "Cliente \"" + cliente.getNomCliente() + "\" salvo com sucesso!");
        return "redirect:/clientes/listar";
    }*/
    
    
    
    @Autowired
    private HistoricoAlteracaoRepository historicoRepo;

    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        cliente.setNomCliente(cliente.getNomCliente().toUpperCase());

        boolean novo = cliente.getCodCliente() == null;

        if (cliente.getDtCadastro() == null) {
            cliente.setDtCadastro(LocalDateTime.now());
        }

        cliente.setAtivo(true);

        if (!novo) {
            Cliente antigo = clienteRepository.findById(cliente.getCodCliente()).orElse(null);
            if (antigo != null) {
                compararELogarAlteracoes(antigo, cliente);
            }
        }

        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente \"" + cliente.getNomCliente() + "\" salvo com sucesso!");
        return "redirect:/clientes/listar";
    }

    private void compararELogarAlteracoes(Cliente antigo, Cliente atual) {
        if (!Objects.equals(antigo.getNomCliente(), atual.getNomCliente())) {
            salvarHistorico(atual.getCodCliente(), "Nome", antigo.getNomCliente(), atual.getNomCliente());
        }
        if (!Objects.equals(antigo.getBairro(), atual.getBairro())) {
            salvarHistorico(atual.getCodCliente(), "Bairro", antigo.getBairro(), atual.getBairro());
        }
        // Repita para outros campos desejados
    }

    private void salvarHistorico(Integer codCliente, String campo, String antigo, String atual) {
        HistoricoAlteracao h = new HistoricoAlteracao();
        h.setCodCliente(codCliente);
        h.setCampoAlterado(campo);
        h.setValorAnterior(antigo);
        h.setValorAtual(atual);
        h.setDataAlteracao(LocalDateTime.now());
        historicoRepo.save(h);
    }



    @GetMapping("/listar")
    public String listarClientes(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String nome,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
                                 Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientePage = clienteRepository.findClientesFiltrados(nome, dataInicio, dataFim, pageable);

        model.addAttribute("clientes", clientePage.getContent());
        model.addAttribute("clientePage", clientePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", clientePage.getTotalPages());
        model.addAttribute("nome", nome);
        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);

        return "listar-clientes";
    }





    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        model.addAttribute("cliente", cliente);
        return "formulario-cliente";
    }

    @PostMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return "redirect:/clientes/listar";
    }
    

    @GetMapping("/historico/{id}")
    public String verHistorico(@PathVariable Integer id, Model model) {
        List<HistoricoAlteracao> historico = historicoRepo.findByCodClienteOrderByDataAlteracaoDesc(id);
        model.addAttribute("historico", historico);
        return "historico-cliente";
    }


    
}
