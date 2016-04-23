es_sintoma(vision_doble).
es_sintoma(vision_nublada).
es_sintoma(vision_borrosa).
es_sintoma(vision_distorcionada).
es_sintoma(dolor_cabeza).
es_sintoma(fatiga_visual).
es_sintoma(entrecerrar_ojos).
es_sintoma(luz_deslumbrante).
es_sintoma(vision_borrosa).
es_sintoma(d_letra_chica). %dificultad para ver letra chica
es_sintoma(d_objeto_cerca).%dificultad ver objetos cercanos
es_sintoma(d_objeto_lejo). %dificultad ver objetos lejanos
es_sintoma(d_conducir_noche). %dificultad para conducir de noche
es_sintoma(mayor_distancia). %ajelar objetos

sintoma_de(dolor_cabeza, miopia).
sintoma_de(entrecerrar_ojos, miopia).
sintoma_de(fatiga_visual, miopia).
sintoma_de(d_objeto_lejo, miopia).

sintoma_de(dolor_cabeza, hipermetropia).
sintoma_de(fatiga_visual, hipermetropia).
sintoma_de(entrecerrar_ojos, hipermetropia).
sintoma_de(vision_borrosa, hipermetropia).

sintoma_de(dolor_cabeza, astigmatismo).
sintoma_de(fatiga_visual, astigmatismo).
sintoma_de(entrecerrar_ojos, astigmatismo).
sintoma_de(vision_borrosa, astigmatismo).
sintoma_de(vision_distorcionada, astigmatismo).
sintoma_de(d_conducir_noche, astigmatismo).

sintoma_de(d_letra_chica, presbicia).
sintoma_de(mayor_distancia, presbicia).
sintoma_de(d_objeto_cerca, presbicia).
sintoma_de(dolor_cabeza, presbicia).
sintoma_de(fatiga_visual, presbicia).


problema_de(X,Y) :- es_sintoma(X), sintoma_de(X,Y).



