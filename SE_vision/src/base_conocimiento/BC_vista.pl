%A
%B
%C
es_sintoma(cansansio_trabajo). %listo cansancio ojos en el trabajo
%D
es_sintoma(dolor_cabeza).%listo
es_sintoma(dolor_ocular).%listo
es_sintoma(d_letra_chica). %listo dificultad para ver letra chica
es_sintoma(d_objeto_cerca).%listo dificultad ver objetos cercanos
es_sintoma(d_objeto_lejo). %listo dificultad ver objetos lejanos
es_sintoma(d_conducir_noche). %listo dificultad para conducir de noche
es_sintoma(desviacion).%listo
%E
es_sintoma(entrecerrar_ojos).%listo
es_sintoma(error_calculo_distancia).%listo
es_sintoma(error_percepcion_relieve).%listo
%F
es_sintoma(fatiga_visual).%listo
es_sintoma(falta_paralelismo).%listo
%G
%H
%I
es_sintoma(irritacion).%listo  despues de forzar vista
es_sintoma(inclinacion).%listo
%L
es_sintoma(luz_deslumbrante).%listo
%M
es_sintoma(mayor_distancia).%listo ajelar objetos
%N
%O
es_sintoma(ojos_bizcos).%listo
es_sintoma(ojos_enrojecidos).%listo
es_sintoma(ojos_hinchados).%listo
es_sintoma(ojos_cansados).%listo
%P
es_sintoma(perdida_agudeza_visual).%listo
es_sintoma(perdida_vision_binocular).%listo
es_sintoma(posicion_anormal_cabeza).%listo
%Q
%R
%S
%T
es_sintoma(tension_ojos).%listo aumento tension ocular
es_sintoma(torticolis).%listo
%U
%V
es_sintoma(vision_doble).%listo
es_sintoma(vision_nublada).%listo
es_sintoma(vision_borrosa).%listo
es_sintoma(vision_distorcionada).%listo
%W
%X
%Y
%Z
%%---ASTIGMATISMO---%%
sintoma_de(dolor_cabeza        , astigmatismo).
sintoma_de(fatiga_visual       , astigmatismo).
sintoma_de(entrecerrar_ojos    , astigmatismo).
sintoma_de(vision_borrosa      , astigmatismo).
sintoma_de(vision_distorcionada, astigmatismo).
sintoma_de(d_conducir_noche    , astigmatismo).
sintoma_de(ojos_enrojecidos    , astigmatismo).
sintoma_de(ojos_hinchados      , astigmatismo).
sintoma_de(ojos_cansados       , astigmatismo).
%%---CATARATA---%%
%%---ESTRABISMO---%%
sintoma_de(falta_paralelismo         , estrabismo).
sintoma_de(perdida_agudeza_visual    , estrabismo).
sintoma_de(posicion_anormal_cabeza   , estrabismo).
sintoma_de(desviacion                , estrabismo).
sintoma_de(inclinacion               , estrabismo).
sintoma_de(torticolis                , estrabismo).
sintoma_de(vision_doble              , estrabismo).
sintoma_de(perdida_vision_binocular  , estrabismo).
sintoma_de(error_calculo_distancia   , estrabismo).
sintoma_de(error_percepcion_relieve  , estrabismo).
%%---HIPERMETROPIA---%%
sintoma_de(dolor_cabeza    , hipermetropia).
sintoma_de(fatiga_visual   , hipermetropia).
sintoma_de(entrecerrar_ojos, hipermetropia).
sintoma_de(vision_borrosa  , hipermetropia).
sintoma_de(dolor_ocular    , hipermetropia).
sintoma_de(ojos_bizcos     , hipermetropia).
%%---MIOPIA---%%
sintoma_de(dolor_cabeza     , miopia).
sintoma_de(entrecerrar_ojos , miopia).
sintoma_de(fatiga_visual    , miopia).
sintoma_de(d_objeto_lejo    , miopia).
sintoma_de(tension_ojos     , miopia).
sintoma_de(irritacion       , miopia).
sintoma_de(cansansio_trabajo, miopia).
%%---PRESBICIA---%%
sintoma_de(d_letra_chica  , presbicia).
sintoma_de(mayor_distancia, presbicia).
sintoma_de(d_objeto_cerca , presbicia).
sintoma_de(dolor_cabeza   , presbicia).
sintoma_de(fatiga_visual  , presbicia).

problema_de(X,Y) :- es_sintoma(X), sintoma_de(X,Y).