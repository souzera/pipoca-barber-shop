package com.br.pipoca.controller;

import com.br.pipoca.dto.*;
import com.br.pipoca.entity.*;
import com.br.pipoca.util.Cargo;
import com.br.pipoca.util.DateConverter;
import com.br.pipoca.util.Hora;
import com.br.pipoca.service.*;
import com.br.pipoca.util.StatusHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@Controller
public class CadastroAdminController {

    @Autowired UsuarioService usuarioService;
    @Autowired FuncionarioService funcionarioService;
    @Autowired ClienteService clienteService;
    @Autowired ProdutoService produtoService;
    @Autowired ServicoService servicoService;
    @Autowired HorarioService horarioService;
    @Autowired AtendimentoService atendimentoService;

    //funcionario

    @GetMapping
    @RequestMapping(value = "/cadastrar/funcionario")
    public ModelAndView cadastrarFuncionario(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroFuncionario.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
    @PostMapping(value = "/cadastrando/funcionario")
    public String createFuncionario(UsuarioDTO usuarioDTO, FuncionarioDTO funcionarioDTO, Model model){
        if (usuarioService.isPresent(usuarioDTO.getLogin())){
            model.addAttribute("erro", "Este usuário já existe.");
            return "admin/cadastroFuncionario";
        }
        if(!(usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha()))){
            model.addAttribute("erro", "As senhas devem ser correspondentes");
            return "admin/cadastroFuncionario";
        }
        Usuario usuario = usuarioDTO.toUsuarioFuncionario();
        Funcionario funcionario = funcionarioDTO.toFuncionario();
        usuarioService.criptarSenha(usuario);
        funcionario.setUsuario(usuarioService.saveUsuario(usuario));
        funcionario.getUsuario().setLogin(usuario.getLogin());
        funcionario.getUsuario().setSenha(usuario.getSenha());
        funcionarioService.saveFuncionario(funcionario);
        //lembrar de mudar esse redirect
        return "redirect:/funcionarios";
    }

    //--------------//
    //clientes

    @GetMapping
    @RequestMapping(value = "/cadastrar/cliente")
    public ModelAndView cadastrarCliente(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroCliente.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
    @PostMapping(value = "/cadastrando/cliente")
    public String createCliente(UsuarioDTO usuarioDTO, ClienteDTO clienteDTO, String dtNascimento, Model model){
        Cliente cliente = clienteDTO.toCliente();
        if(dtNascimento!=""){
            Date data = DateConverter.textToSQLDate(dtNascimento);
            cliente.setDtNascimento(data);
            System.out.println(cliente.getDtNascimento());
        }
        if (usuarioDTO.getLogin() != "") {
            if (usuarioService.isPresent(usuarioDTO.getLogin())) {
                model.addAttribute("erro", "Este usuário já existe.");
                return "admin/cadastroCliente";
            }
            if (!(usuarioDTO.getSenha().equals(usuarioDTO.getConfirmaSenha()))) {
                model.addAttribute("erro", "As senhas devem ser correspondentes");
                return "admin/cadastroCliente";
            }
            Usuario usuario = usuarioDTO.toUsuarioCliente();
            usuarioService.criptarSenha(usuario);
            usuarioService.saveUsuario(usuario);
            cliente.setUsuario(usuarioService.findByLogin(usuario.getLogin()));
        }
        if (cliente.getNome()!=null && cliente.getTelefone() != null) {
            clienteService.saveCliente(cliente);
        }
        //lembrar de mudar esse redirect
        return "redirect:/login";
    }

    //==================
    //Produtos

    @GetMapping
    @RequestMapping(value = "/registro/produto")
    public ModelAndView cadastrarProduto(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroProduto.html");
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
    @PostMapping(value = "/registrando/produto")
    public String createProduto(ProdutoDTO produtoDTO){
        Produto produto = produtoDTO.toProduto();
        produtoService.saveProduto(produto);
        return "redirect:/registro/produto";
    }

    //====================
    //Serviços
    @GetMapping
    @RequestMapping(value = "/registro/servico")
    public ModelAndView cadastrarServico(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/cadastroServico.html");Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }
    @PostMapping(value = "/registrando/servico")
    public String createServico(ServicoDTO servicoDTO){
        Servico servico = servicoDTO.toServico();
        servicoService.saveServico(servico);
        return "redirect:/registro/servico";
    }

    //================
    //Agenda

    @GetMapping
    @RequestMapping(value = "/agendar/step1")
    public ModelAndView buscarAgenda(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/registroAgendamentoStep1.html");

        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(usuarioService.findByLogin(CookieService.getCookieValue(request,"login")), modelAndView);
        switch (u.getTipoUsuario()){
            case ADM:
            case DEV:
            case SUPER:
            case ATENDENTE:
                modelAndView.addObject("funcionarios", funcionarioService.findByCargo(Cargo.BARBEIRO));
                break;
            case BARBEIRO:
                modelAndView.addObject("funcionario", funcionarioService.findByLogin(u.getLogin()));
                break;
        }
        return modelAndView;
    }

    @PostMapping(value = "/agendar/to-step-2")
    public String agendarToStep2(Date date, long funcionario_id){
        return "redirect:/agendar/" + funcionario_id + "&"  + date;
    }

    @GetMapping(value = "/agendar/{funcionario}&{date}")
    public ModelAndView agendar(@PathVariable(value = "funcionario") long funcionario_id, @PathVariable(value = "date") Date date, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/registroAgendamentoStep2.html");
        modelAndView.addObject("date", date);
        modelAndView.addObject("funcionario", funcionarioService.findById(funcionario_id));
        modelAndView.addObject("clientes", clienteService.clientes());
        modelAndView.addObject("servicos", servicoService.servicos());
        modelAndView.addObject("vagas", horarioService.vagas(funcionario_id, date));
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/agendando")
    public String createAgenda(HorarioDTO horarioDTO, AtendimentoDTO atendimentoDTO, Model model, HttpServletRequest request) throws IOException {
        //criar novo horario
        Horario horario = horarioDTO.toHorario();
        horario.setFuncionario(funcionarioService.findById(horarioDTO.getFuncionarioId()));
        //cria atendimento
        Atendimento atendimento = atendimentoDTO.toAtendimento();
        atendimento.setCliente(clienteService.buscarPorID(atendimentoDTO.getCliente_id()));
        atendimento.setServico(servicoService.buscarPorID(atendimentoDTO.getServico_id()));

        if (horarioService.isDisponivel(horario)){
            long id_horario = horarioService.saveHorario(horario).getId();
            horarioService.buscarPorId(id_horario).setFuncionario(horario.getFuncionario());
            horarioService.buscarPorId(id_horario).setHora(horario.getHora());
            horarioService.buscarPorId(id_horario).setStatusHorario(horario.getStatusHorario());
            atendimento.setHorario(horarioService.buscarPorId(id_horario));
            atendimento.getHorario().setStatusHorario(StatusHorario.OCIOSO);
        }else {
            model.addAttribute("hora", Hora.values());
            model.addAttribute("clientes", clienteService.clientes());
            model.addAttribute("barbeiros", funcionarioService.findByCargo(Cargo.BARBEIRO));
            model.addAttribute("servicos", servicoService.servicos());
            model.addAttribute("erro", "Este horario está ocupado. Caso necessário consulte a lista de agendamentos.");
            return "admin/registroAgendamento";
        }

        atendimentoService.agendar(atendimento);
        return "redirect:/agendamentos";

    }

    //=================================================================================================
    //VENDA

    @GetMapping
    @RequestMapping(value = "/registro/venda")
    public ModelAndView registraVenda(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("admin/registroVenda");
        modelAndView.addObject("clientes", clienteService.clientes());
        modelAndView.addObject("produtos", produtoService.produtos());
        Usuario u = usuarioService.findByLogin(CookieService.getCookieValue(request,"login"));
        modelAndView.addObject("usuario", u);
        usuarioService.addPass(u, modelAndView);
        return modelAndView;
    }


    //TODO implentar controle para venda de produtos
    @PostMapping
    @RequestMapping(value = "/registrar/venda")
    public String createVendaStep1(Long produto_id, HttpServletResponse response) throws IOException {
        return "olá mundo";
    }
}
