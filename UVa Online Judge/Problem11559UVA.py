while True:
    try:
        datos = list(map(int, input().split()))
        REF = 1 << 30; costo_minimo = REF 
    except:
        break

    for x in range(datos[2]):
        costo_hotel = int(input())
        camas_disponibles = map(int, input().split())
        for num_camas in camas_disponibles:
            costo = datos[0] * costo_hotel
            if num_camas >= datos[0] and costo <= datos[1]:
                costo_minimo = min(costo_minimo, costo)

    print(costo_minimo if costo_minimo != REF  else "stay home")
