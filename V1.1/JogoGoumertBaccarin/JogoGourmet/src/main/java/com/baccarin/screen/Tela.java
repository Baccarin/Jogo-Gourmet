package com.baccarin.screen;

import com.baccarin.reg.Classificacao;
import com.baccarin.reg.Prato;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author baccarin
 */
public class Tela extends JFrame {

    private String titulo;
    public ArrayList<Prato> pratos = new ArrayList<Prato>();
    public JTextField txtCadPrato = new JTextField("Prato");

    public Tela(String titulo) {
        this.titulo = titulo;
        this.initObject();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public void init() {
        JFrame frame = new JFrame();
        this.beginScreen(frame);
    }

    public void beginScreen(JFrame frame) {
        int resp = JOptionPane.showConfirmDialog(frame, "Pense em um prato que voc� gosta", this.titulo,
                JOptionPane.DEFAULT_OPTION);
        if (resp == 0) {
            this.questionScreen(frame);
        }
    }

    public void victory(JFrame frame) {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
        this.quitScreen(frame);
    }

    public int question(JFrame frame, Prato prato) {
        if (prato.getClassificacao() == null) {
            return this.nameQuestion(frame, prato);
        }
        return JOptionPane.showConfirmDialog(frame,
                String.format("O prato que voc� pensou foi %s", prato.getClassificacao().getClassificacao()),
                this.titulo, JOptionPane.YES_NO_OPTION);
    }

    public int nameQuestion(JFrame frame, Prato prato) {
        return JOptionPane.showConfirmDialog(frame, String.format("O prato que voc� pensou foi %s", prato.getNome()),
                this.titulo, JOptionPane.YES_NO_OPTION);
    }

    public void questionScreen(JFrame frame) {
        boolean confirmed = false;
        Classificacao cla = null;
        for (Prato prato : pratos) {
            if (!confirmed) {
                if (question(frame, prato) == JOptionPane.YES_OPTION) {
                    if (prato.getClassificacao().getClassificacao().equals("massa")) {
                        cla = pratos.get(0).getClassificacao();
                    }
                    for (Prato p : pratos) {
                        if (p.getClassificacao().getClassificacao().equals("massa")) {
                            if (nameQuestion(frame, p) == JOptionPane.YES_OPTION) {
                                confirmed = true;
                                this.victory(frame);
                            }
                        }
                    }
                    if (!confirmed) {
                        this.screenCad(frame, cla);
                    }

                } else {
                    for (Prato p : pratos) {
                        if (!p.getClassificacao().getClassificacao().equals("massa") && cla == null && question(frame, p) == JOptionPane.YES_OPTION) {
                            cla = p.getClassificacao();
                        }
                        if (!p.getClassificacao().getClassificacao().equals("massa")) {
                            if (nameQuestion(frame, p) == JOptionPane.YES_OPTION) {
                                confirmed = true;
                                this.victory(frame);
                            }
                        }
                    }
                    if (!confirmed) {
                        this.screenCad(frame, cla);
                    }

                }

            } else {
                this.beginScreen(frame);
            }
        }
        if (!confirmed) {
            this.screenCad(frame, cla);
        }
    }

    public void screenCad(JFrame frame, Classificacao cla) {
        String newPrato = JOptionPane.showInputDialog("Qual o prato que voc� pensou?");
        Prato prato = new Prato(newPrato);
        if (cla != null){
            prato.setClassificacao(cla);
        }else {
            prato = this.addClassification(prato, frame);
        }
        this.finalizePrato(frame, prato);

    }

    public Prato addClassification(Prato prato, JFrame frame) {
        String newClassificacao = JOptionPane
                .showInputDialog(String.format("%s � ____ mas %s n�o", prato.getNome(), pratos.get(0).getNome()));
        prato.setClassificacao(new Classificacao(newClassificacao));
        return prato;
    }

    public void finalizePrato(JFrame frame, Prato prato) {
        this.printPrato(prato);
        this.janelaPrato(frame, prato);
        this.pratos.add(prato);
        this.quitScreen(frame);
    }

    public void initObject() {
        Prato lasanha = new Prato("Lasanha", (new Classificacao("massa")));
        Prato bolo = new Prato("Bolo de chocolate", (new Classificacao("bolo")));
        this.pratos.add(lasanha);
        this.pratos.add(bolo);
    }

    public void printPrato(Prato p) {
        System.out.println(String.format("O prato adicionado foi: [%s] que � um(a) [%s]", p.getNome(), p.getClassificacao().getClassificacao()));
    }

    public void janelaPrato(JFrame frame, Prato p) {
        JOptionPane.showConfirmDialog(frame,
                (String.format("O prato adicionado foi: [%s] que � um(a) [%s]", p.getNome(), p.getClassificacao().getClassificacao())),
                this.titulo, JOptionPane.DEFAULT_OPTION);
    }

    public void quitScreen(JFrame frame) {
        int keep = JOptionPane.showConfirmDialog(frame, "Deseja continuar jogando?", this.titulo,
                JOptionPane.YES_NO_OPTION);
        if (keep == JOptionPane.YES_OPTION) {
            this.beginScreen(frame);
        } else {
            System.exit(0);
        }
    }

}
