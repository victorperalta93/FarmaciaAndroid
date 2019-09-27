package ugr.victorperalta.sufarmacia;

// clicklistener para los botones 'añadir a la cesta' del RecyclerView
public interface ClickListener {
    void onPositionClicked(int position);

    void onLongClicked(int position);
}