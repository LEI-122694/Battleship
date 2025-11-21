# checklist

## Reports
* [passed @LEI-122694] C1 Reports
    * Gerar reports html através do run with coverage
    * Mover reports para pasta específica
    * Verificar se os reports estão corretos
    * Analisar cobertura de código


## Ships test cases
* [passed @LEI-122694] C3 BargeTest
    tags: #unit-tests #test-cases
    * Step 1: Instanciar uma `Barge` com `bearing = Compass.NORTH` e `startPos = new Position(0,0)`.
    * Step 2: Verificar que `getCategory()` retorna "Barca", `getSize()` retorna 1, e `getBearing()` corresponde ao fornecido.
    * Step 3: Instanciar uma `Barge` em `Position(row=4, col=7)` e verificar que `getPositions()` tem tamanho 1 e que essa posição coincide com (4,7).

* [passed @LEI-122694] C4 CaravelTest
    tags: #unit-tests #test-cases
    * Step 1: Instanciar uma `Caravel` com `Compass.NORTH` em `Position(0,0)` e verificar `getCategory()` == "Caravela" e `getSize()` == 2.
    * Step 2: Para orientação `NORTH` (vertical) criar `Caravel(Compass.NORTH, new Position(2,5))` e verificar `getPositions()` tem 2 posições: a primeira é (2,5) e a segunda é (3,5).
    * Step 3: Para orientação `EAST` (horizontal) criar `Caravel(Compass.EAST, new Position(3,3))` e verificar `getPositions()` tem 2 posições: (3,3) e (3,4).

* [passed @LEI-122694] C5 CarrackTest
    tags: #unit-tests #test-cases
    * Step 1: Instanciar uma `Carrack` com `Compass.NORTH` em `Position(0,0)` e verificar `getCategory()` == "Nau" e `getSize()` == 3.
    * Step 2: Teste vertical (`SOUTH`/`NORTH`): criar `Carrack(Compass.SOUTH, new Position(1,1))` e verificar que ocupa (1,1), (2,1), (3,1).
    * Step 3: Teste horizontal (`WEST`/`EAST`): criar `Carrack(Compass.WEST, new Position(5,2))` e verificar que ocupa (5,2), (5,3), (5,4).

* [passed @LEI-122694] C6 FleetTest
    tags: #unit-tests #test-cases
    * Step 1 (AddShip success): Instanciar `Fleet`, criar `Frigate` e chamar `fleet.addShip(frigate)`; verificar que retorna `true` e `fleet.getShips().size() == 1`.
    * Step 2 (Fleet full): Adicionar `IFleet.FLEET_SIZE` navios (por exemplo `new Frigate(...)`) e verificar que `fleet.addShip(frigate)` passa a retornar `false` quando a frota está cheia.
    * Step 3 (Queries) - preparação: adicionar `frigate` e `galleon` à frota.
        * Sub-step: `getShips()` deve devolver 2 navios e conter ambos.
        * Sub-step: `getShipsLike("Galeao")` deve devolver uma lista com o `galleon`.
        * Sub-step: `getFloatingShips()` inicialmente devolve 2; afundar o `frigate` (shoot em todas as suas posições) e então `getFloatingShips()` deve devolver 1 contendo o `galleon`.
        * Sub-step: `shipAt(new Position(0,0))` devolve `frigate`; `shipAt(new Position(5,5))` devolve `galleon`; `shipAt(new Position(9,9))` devolve `null`.
    * Step 4 (Constraints): `addShip` deve recusar um `Frigate(Compass.EAST, new Position(9,8))` porque sairia do tabuleiro; também deve recusar um navio demasiado próximo (ex.: adicionar um `Frigate` em (0,0) e tentar outro em (1,0) deve retornar `false`).

* [passed @LEI-122694] C7 FrigateTest
    tags: #unit-tests #test-cases
    * Step 1: Verificar que `Frigate(...).getSize()` == 4.
    * Step 2 (NORTH): `Frigate(Compass.NORTH, new Position(2,3))` deve ter 4 posições: (2,3),(3,3),(4,3),(5,3) e `occupies(new Position(2,3))` deve ser `true`.
    * Step 3 (SOUTH): `Frigate(Compass.SOUTH, new Position(1,4))` deve ocupar (1,4),(2,4),(3,4),(4,4).
    * Step 4 (EAST): `Frigate(Compass.EAST, new Position(5,2))` deve ocupar (5,2),(5,3),(5,4),(5,5) e os métodos de limites (`getTopMostPos()`, `getBottomMostPos()`, `getLeftMostPos()`, `getRightMostPos()`) devem refletir a posição correta (no teste: 5,5,2,5 respectivamente).
    * Step 5 (WEST): `Frigate(Compass.WEST, new Position(3,1))` deve ocupar 4 colunas consecutivas na linha 3.
    * Step 6 (shoot & stillFloating): Instanciar `Frigate(Compass.EAST, new Position(0,0))`, verificar `stillFloating()` inicialmente `true`, dar `shoot()` em todas as posições e verificar que cada `IPosition.isHit()` passa a `true` e no final `stillFloating()` é `false`.
    * Step 7 (tooCloseTo): `Frigate(Compass.EAST, new Position(0,0))` é tooCloseTo outra `Frigate(Compass.EAST, new Position(1,0))` e não é tooCloseTo `Frigate` em (5,5).

* [passed @LEI-122694] C8 GalleonTest
    tags: #unit-tests #test-cases
    * Step 1: Verificar que `Galleon(...).getSize()` == 5.
    * Step 2 (NORTH shape): `Galleon(Compass.NORTH, new Position(2,3))` deve conter posições: (2,3),(2,4),(2,5),(3,4),(4,4); `getTopMostPos()` == 2 e `getBottomMostPos()` == 4.
    * Step 3 (SOUTH shape): `Galleon(Compass.SOUTH, new Position(1,2))` deve conter: (1,2),(2,2),(3,1),(3,2),(3,3); topMost 1 bottomMost 3.
    * Step 4 (EAST shape): `Galleon(Compass.EAST, new Position(4,5))` deve conter: (4,5),(5,3),(5,4),(5,5),(6,5); topMost 4 bottomMost 6.
    * Step 5 (WEST shape): `Galleon(Compass.WEST, new Position(3,2))` deve conter: (3,2),(4,2),(4,3),(4,4),(5,2).
    * Step 6 (shoot & stillFloating): Verificar que ao disparar em todas as posições exceto a última o navio ainda está a flutuar, e ao disparar a última posição `stillFloating()` passa a `false`.
    * Step 7 (tooCloseTo): `Galleon(0,0)` demasiado perto de `Galleon(2,1)` (true) e não demasiado perto de `Galleon(10,10)` (false).

* [passed @LEI-122694] C9 IFleetTest
    tags: #unit-tests #test-cases
    * Step 1: Usar `IFleet fleet = new Fleet()`; `fleet.addShip(frigate)` deve devolver `true` e `fleet.getShips().size() == 1`.
    * Step 2: Adicionar `frigate` e `galleon` e verificar `getShips()` tem ambos.
    * Step 3: `getShipsLike("Galeao")` devolve lista com `galleon`.
    * Step 4: `getFloatingShips()` inicialmente retorna 2; afundar `frigate` e verificar que retorna apenas o `galleon`.
    * Step 5: `shipAt` comporta-se como em `FleetTest` (posições 0,0 e 5,5 retornam os navios adequados; 9,9 retorna `null`).
    * Step 6: `printStatus()` deve executar sem lançar exceções.

* [passed @LEI-122694] C10 PositionTest
    tags: #unit-tests #test-cases
    * Step 1: Testar `getRow()` usando `new Position(5,2)` e verificar que `getRow()` retorna `5`.
    * Step 2: Testar `getColumn()` usando `new Position(5,2)` e verificar que `getColumn()` retorna `2`.
    * Step 3: Testar `hashCode()` criando duas posições iguais (`new Position(1,1)` e `new Position(1,1)`) e verificar que `hashCode()` é igual; depois alterar estado (`occupy()` e `shoot()`) e verificar que o `hashCode()` muda.
    * Step 4: Testar `equals()` com posições iguais e diferentes, e verificar comportamento contra `null` e objetos de outro tipo.
    * Step 5: Testar `isAdjacentTo()` com posições adjacentes (esquerda), diagonais e não adjacentes (distante).
    * Step 6: Testar `occupy()` marca a posição como ocupada (`isOccupied()` passa de false para true).
    * Step 7: Testar `shoot()` marca a posição como atingida (`isHit()` passa de false para true).
    * Step 8: Testar `isOccupied()` e `isHit()` refletem corretamente os estados após `occupy()` e `shoot()`.
    * Step 9: Testar `toString()` para garantir que contém as palavras "Linha" e "Coluna" e os valores da posição (ex.: "7" e "8").

* [passed @LEI-122694] C11 IPositionTest
    tags: #unit-tests #test-cases
    * Step 1: Testar `getRow()` via interface `IPosition p = new Position(2,3)` e verificar que retorna 2.
    * Step 2: Testar `getColumn()` via interface `IPosition p = new Position(4,1)` e verificar que retorna 1.
    * Step 3: Testar `equals()` entre `IPosition` com mesmas e diferentes coordenadas, e comparar com outro tipo para garantir desigualdade.
    * Step 4: Testar `isAdjacentTo()` com a mesma posição, posição acima, diagonal e posição distante (esperado: true, true, true, false).
    * Step 5: Testar `occupy()` via interface altera `isOccupied()` para true.
    * Step 6: Testar `shoot()` via interface altera `isHit()` para true.
    * Step 7: Testar `isOccupied()` e `isHit()` refletem corretamente os estados após as chamadas anteriores.

* [passed @LEI-122694] C12 CompassTest
    tags: #unit-tests #test-cases
    * Step 1: Verificar `getDirection()` para cada enum: `NORTH->'n'`, `SOUTH->'s'`, `EAST->'e'`, `WEST->'o'`, `UNKNOWN->'u'`.
    * Step 2: `toString()` deve devolver o código de direção como string ("n","s","e","o","u").
    * Step 3: `charToCompass()` deve mapear caracteres minúsculos ('n','s','e','o') para o enum correspondente, caracteres desconhecidos e maiúsculas devem devolver `UNKNOWN`.
    * Step 4: `values()` deve retornar os 5 valores no array na ordem esperada e `valueOf()` deve funcionar para nomes válidos; `valueOf("INVALID")` deve lançar `IllegalArgumentException`.

* [passed @LEI-122694] C13 ShipTest
    tags: #unit-tests #test-cases
    * Step 1: Preparação: criar um `Ship` concreto usado nos testes — aqui uma `Caravel(Compass.EAST, new Position(3,4))`.
    * Step 2: `getCategory()` deve devolver a categoria correta (no teste: "caravela", comparado em lowercase).
    * Step 3: `getBearing()` deve devolver `Compass.EAST` para a instância criada.
    * Step 4: `getPosition()` deve devolver a posição base usada na criação (3,4).
    * Step 5: `getPositions()` deve devolver a lista de posições ocupadas (para a Caravel: tamanho 2) e verificar que são `(3,4)` e `(3,5)`.
    * Step 6: `stillFloating()` é `true` quando pelo menos uma posição não foi atingida — atirar em (3,4) e verificar `stillFloating()` == true.
    * Step 7: `stillFloating()` é `false` quando todas as posições estão atingidas — atirar em todas as posições (3,4) e (3,5) e verificar `false`.
    * Step 8: `getTopMostPos()` deve devolver 3 (linha topmost para a configuração dada).
    * Step 9: `getBottomMostPos()` deve devolver 3.
    * Step 10: `getLeftMostPos()` deve devolver 4.
    * Step 11: `getRightMostPos()` deve devolver 5.
    * Step 12: `occupies()` deve devolver `true` para posições ocupadas (3,4) e (3,5) e `false` para posições não ocupadas (ex.: (0,0)).
    * Step 13: `tooCloseTo(IPosition)` deve devolver `true` para uma posição adjacente (3,6) e `false` para uma posição distante (0,0).
    * Step 14: `tooCloseTo(IShip)` deve devolver `true` quando outro navio (ex.: `Caravel` em (3,6)) estiver adjacente e `false` quando estiver afastado (ex.: `Caravel(Compass.NORTH, new Position(0,0))`).
    * Step 15: `shoot()` marca a posição como atingida quando coincide: atirar em (3,4) e verificar que `getPositions().get(0).isHit()` é `true`.
    * Step 16: `shoot()` não marca nada em caso de falha: atirar em (9,9) e verificar que nenhuma posição de `getPositions()` está `isHit()`.
    * Step 17: `toString()` não deve ser `null`, deve conter a categoria ("Caravela") e as palavras "Linha" e "Coluna" vindas do `Position.toString()`.


## Unit tests
* [passed @LEI-122694] C2 Unit tests
    * executar mvn test ou run tests no IDE
    * verificar o resultado dos testes
    * analisar falhas e erros


