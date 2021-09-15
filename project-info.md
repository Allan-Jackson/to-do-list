## Necessidades (requisitos)

- [x] Activity para alterar a nota;

- [x] Utilizar listeners para viewHolder do RecyclerView;

- [ ] Utilizar SQLite para persistir os dados;

- [x] Criar menu utilizando imageViews para mudar o layout da lista;

- [x] Utilizar Bundle para mudanças de configuração; (por enquanto)

- [ ] Aplicar o uso de ViewModel para persistir mudanças de configuração; (depois)

- [x] Utilizar toolbar para retornar à lista; (depois)

- [ ] FAB para adicionar novas notas; (depois)

- [ ] Utilizar toolbar personalizada nas activities; (depois)

- [ ] Deletar nota ao selecionar; (depois)

- [ ] Selecionar e excluir notas em batch; (depois)

- [ ] Criar checkboxes para cada novo parágrafo na nota; (depois)



## Sequência lógica

Activity Principal -> Lista de notas;

Activity Principal -> menu superior para alterar layout da lista;

Activity Principal -> botão para adicionar as notas;

ViewHolder e Adapter para mesclar dados da lista;

Repositório para os dados a serem lidos/gravados; (função para gravar nota(s))

Listeners para eventos na ViewHolder; (abrir activity editar nota)

Activity Adicionar/Editar Nota -> input para título

Activity Adicionar/Editar Nota -> input para conteúdo

Activity Adicionar/Editar Nota -> toolbar para retornar à Activity Principal



#### Versão 1

1. Activity Principal -> Lista de notas;

2. Activity Principal -> menu superior com imageViews (LinearLayout) para alterar layout da lista;

3. Activity Principal -> botão comum para adicionar as notas;

4. Definir layout para os itens da lista; (adicionado: necessário melhorar pois está feio) 

5. ViewHolder e Adapter para mesclar dados da lista;

6. Repositório para os dados a serem lidos/gravados (mock);

7. Listeners para eventos na ViewHolder; (ir para a Activity de edição da nota) 

8. Activity Adicionar/Editar Nota -> input para título

9. Activity Adicionar/Editar Nota -> input para conteúdo

10. Activity Adicionar/Editar Nota -> retornar à Activity Principal apenas com o botão back do smartphone (alterado: retornando com Up button padrão)



### Eventos

Viewholder -> clicar na viewholder deve levar para a página de edição da nota

Viewholder -> pressionar viewholder deve selecioná-la e aparecer menu superior com opções

Viewholder -> clicar na viewholder após ter selecionado alguma, deve selecionar essa outra. Caso a viewholder seja a mesma que estava selecionada, deve tirar a seleção

botão Deletar -> botão deletar deve excluir as notas (viewholders) selecionadas

botão Adicionar -> deve levar à página de adição de notas

botão de Voltar/Salvar -> deve retornar à Activity principal, salvando as modificações da nota










