package br.com.locaweb.relatorioclientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.locaweb.relatorioclientes.model.Cliente;
import br.com.locaweb.relatorioclientes.repository.ClienteRepository;
import br.com.locaweb.relatorioclientes.util.ConvertRegiao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /*/ public Map<String, Object> gerarRelatorio() {
        List<Cliente> clientes = clienteRepository.findByAtivoOrderByCodClienteAscLogradouroAsc(true);

        Map<String, Integer> contadorBairros = new TreeMap<>();
        Map<String, Integer> contadorRegioes = new TreeMap<>();
        ConvertRegiao convertRegiao = new ConvertRegiao();

        for (Cliente cliente : clientes) {
            String bairro = cliente.getBairro();
            String nomeRegiao = convertRegiao.ExibirRegiao(cliente.getRegiao());

            contadorBairros.put(bairro, contadorBairros.getOrDefault(bairro, 0) + 1);
            contadorRegioes.put(nomeRegiao, contadorRegioes.getOrDefault(nomeRegiao, 0) + 1);
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("bairros", contadorBairros);
        resultado.put("regioes", contadorRegioes);
        resultado.put("total", clientes.size());

        return resultado;
    }*/

    public List<Cliente> getClientesAtivos() {
        return clienteRepository.findByAtivoOrderByCodClienteAscLogradouroAsc(true);
    }
    
    public Map<String, Object> gerarRelatorio() {
        // Consultar clientes ativos do banco de dados
        List<Cliente> clientes = clienteRepository.findByAtivoOrderByCodClienteAscLogradouroAsc(true);
        
        // Mapas para contar pontos por bairro e por região
        Map<String, Integer> contadorBairros = new TreeMap<>();
        Map<String, Integer> contadorRegioes = new TreeMap<>();
        
        // Instanciar a classe que converte a região num nome legível
        ConvertRegiao convertRegiao = new ConvertRegiao();

        // Iterar sobre a lista de clientes para preencher os mapas
        for (Cliente cliente : clientes) {
            String bairro = cliente.getBairro();
            String nomeRegiao = convertRegiao.exibirNome(cliente.getRegiao());

            // Atualizar a contagem de bairros
            contadorBairros.put(bairro, contadorBairros.getOrDefault(bairro, 0) + 1);
            
            // Atualizar a contagem de regiões
            contadorRegioes.put(nomeRegiao, contadorRegioes.getOrDefault(nomeRegiao, 0) + 1);
        }

        // Criar o mapa de resultados com os dados do relatório
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("bairros", contadorBairros);  // Contagem por bairro
        resultado.put("regioes", contadorRegioes);  // Contagem por região
        resultado.put("total", clientes.size());    // Total de clientes
        
        // Verificando os dados retornados no console
        System.out.println("Relatório gerado:");
        System.out.println("Bairros: " + contadorBairros);
        System.out.println("Regioes: " + contadorRegioes);
        System.out.println("Total de Clientes: " + clientes.size());

        // Retornar o relatório gerado
        return resultado;
    }

    
}
