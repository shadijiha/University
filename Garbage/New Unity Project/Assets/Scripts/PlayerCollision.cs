using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerCollision : MonoBehaviour
{
    public PlayerMouvement mouvement;
    private void OnCollisionEnter(Collision collision)
    {
        if (collision.collider.tag == "Obstacle") {
            mouvement.enabled = false;
            FindObjectOfType<GameManager>().endGame();
        }
        
    }

}
